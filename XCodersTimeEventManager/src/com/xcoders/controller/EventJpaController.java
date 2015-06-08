/* 
 *File info : JpaController class for EventJpaController.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */


package com.xcoders.controller;

import com.xcoders.controller.exceptions.NonexistentEntityException;
import com.xcoders.model.Event;

import java.io.Serializable;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.xcoders.model.EventCalendar;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventJpaController implements Serializable {

    public EventJpaController() {
        this.emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Event event) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventCalendar calendar = event.getCalendar();
            if (calendar != null) {
                calendar = em.getReference(calendar.getClass(), calendar.getId());
                event.setCalendar(calendar);
            }
            em.persist(event);
            /*if (calendar != null) {
                calendar.createEvent().add(event);
                calendar = em.merge(calendar);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Event event) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Event persistentEvent = em.find(Event.class, event.getId());
            EventCalendar calendarOld = persistentEvent.getCalendar();
            EventCalendar calendarNew = event.getCalendar();
            if (calendarNew != null) {
                calendarNew = em.getReference(calendarNew.getClass(), calendarNew.getId());
                event.setCalendar(calendarNew);
            }
            event = em.merge(event);
            /*if (calendarOld != null && !calendarOld.equals(calendarNew)) {
                calendarOld.createEvent().remove(event);
                calendarOld = em.merge(calendarOld);
            }
            if (calendarNew != null && !calendarNew.equals(calendarOld)) {
                calendarNew.createEvent().add(event);
                calendarNew = em.merge(calendarNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = event.getId();
                if (findEvent(id) == null) {
                    throw new NonexistentEntityException("The event with id " + id + " no longer exists.");
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
            Event event;
            try {
                event = em.getReference(Event.class, id);
                event.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The event with id " + id + " no longer exists.", enfe);
            }
            /*EventCalendar calendar = event.getCalendar();
            if (calendar != null) {
                calendar.createEvent().remove(event);
                calendar = em.merge(calendar);
            }*/
            em.remove(event);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Event> findEventEntities() {
        return findEventEntities(true, -1, -1);
    }

    public List<Event> findEventEntities(int maxResults, int firstResult) {
        return findEventEntities(false, maxResults, firstResult);
    }

    private List<Event> findEventEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Event.class));
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

    public Event findEvent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Event.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Event> rt = cq.from(Event.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
