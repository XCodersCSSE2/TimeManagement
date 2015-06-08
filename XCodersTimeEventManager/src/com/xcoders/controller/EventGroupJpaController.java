/* 
 *File info : JpaController class for EventGroupJ.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.controller;

import com.xcoders.controller.exceptions.NonexistentEntityException;
import com.xcoders.model.EventGroup;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class EventGroupJpaController implements Serializable {

    public EventGroupJpaController() {
        this.emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventGroup eventGroup) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(eventGroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventGroup eventGroup) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            eventGroup = em.merge(eventGroup);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventGroup.getId();
                if (findEventGroup(id) == null) {
                    throw new NonexistentEntityException("The eventGroup with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventGroup eventGroup;
            try {
                eventGroup = em.getReference(EventGroup.class, id);
                eventGroup.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventGroup with id " + id + " no longer exists.", enfe);
            }
            em.remove(eventGroup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventGroup> findEventGroupEntities() {
        return findEventGroupEntities(true, -1, -1);
    }

    public List<EventGroup> findEventGroupEntities(int maxResults, int firstResult) {
        return findEventGroupEntities(false, maxResults, firstResult);
    }

    private List<EventGroup> findEventGroupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventGroup.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EventGroup findEventGroup(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventGroup.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventGroupCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventGroup> rt = cq.from(EventGroup.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
