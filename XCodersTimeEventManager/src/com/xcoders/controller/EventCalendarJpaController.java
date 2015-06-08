/* 
 *File info : JpaController class for EventCalendar.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.controller;

import com.xcoders.controller.exceptions.NonexistentEntityException;
import com.xcoders.model.EventCalendar;

import java.io.Serializable;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.xcoders.model.EventMember;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventCalendarJpaController implements Serializable {

    public EventCalendarJpaController() {
        this.emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventCalendar eventCalendar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventMember owner = eventCalendar.getOwner();
            if (owner != null) {
                owner = em.getReference(owner.getClass(), owner.getId());
                eventCalendar.setOwner(owner);
            }
            em.persist(eventCalendar);
            if (owner != null) {
                owner.getCalendars().add(eventCalendar);
                owner = em.merge(owner);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventCalendar eventCalendar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventCalendar persistentEventCalendar = em.find(EventCalendar.class, eventCalendar.getId());
            EventMember ownerOld = persistentEventCalendar.getOwner();
            EventMember ownerNew = eventCalendar.getOwner();
            if (ownerNew != null) {
                ownerNew = em.getReference(ownerNew.getClass(), ownerNew.getId());
                eventCalendar.setOwner(ownerNew);
            }
            eventCalendar = em.merge(eventCalendar);
            if (ownerOld != null && !ownerOld.equals(ownerNew)) {
                ownerOld.getCalendars().remove(eventCalendar);
                ownerOld = em.merge(ownerOld);
            }
            if (ownerNew != null && !ownerNew.equals(ownerOld)) {
                ownerNew.getCalendars().add(eventCalendar);
                ownerNew = em.merge(ownerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventCalendar.getId();
                if (findEventCalendar(id) == null) {
                    throw new NonexistentEntityException("The eventCalendar with id " + id + " no longer exists.");
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
            EventCalendar eventCalendar;
            try {
                eventCalendar = em.getReference(EventCalendar.class, id);
                eventCalendar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventCalendar with id " + id + " no longer exists.", enfe);
            }
            EventMember owner = eventCalendar.getOwner();
            if (owner != null) {
                owner.getCalendars().remove(eventCalendar);
                owner = em.merge(owner);
            }
            em.remove(eventCalendar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventCalendar> findEventCalendarEntities() {
        return findEventCalendarEntities(true, -1, -1);
    }

    public List<EventCalendar> findEventCalendarEntities(int maxResults, int firstResult) {
        return findEventCalendarEntities(false, maxResults, firstResult);
    }

    private List<EventCalendar> findEventCalendarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventCalendar.class));
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

    public EventCalendar findEventCalendar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventCalendar.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventCalendarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventCalendar> rt = cq.from(EventCalendar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
