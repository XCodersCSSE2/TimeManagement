/* 
 *File info : JpaController class for EventCalendar_EventMember.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.controller;

import com.xcoders.controller.exceptions.NonexistentEntityException;

import java.io.Serializable;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventCalendar_EventMember;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventCalendar_EventMemberJpaController implements Serializable {

    public EventCalendar_EventMemberJpaController() {
        this.emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventCalendar_EventMember eventCalendar_EventMember) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventCalendar calendar = eventCalendar_EventMember.getCalendar();
            if (calendar != null) {
                calendar = em.getReference(calendar.getClass(), calendar.getId());
                eventCalendar_EventMember.setCalendar(calendar);
            }
            em.persist(eventCalendar_EventMember);
           /* if (calendar != null) {
                calendar.getNewEventCalendar_EventMemberRecord().add(eventCalendar_EventMember);
                calendar = em.merge(calendar);
            }*/
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventCalendar_EventMember eventCalendar_EventMember) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventCalendar_EventMember persistentEventCalendar_EventMember = em.find(EventCalendar_EventMember.class, eventCalendar_EventMember.getId());
            EventCalendar calendarOld = persistentEventCalendar_EventMember.getCalendar();
            EventCalendar calendarNew = eventCalendar_EventMember.getCalendar();
            if (calendarNew != null) {
                calendarNew = em.getReference(calendarNew.getClass(), calendarNew.getId());
                eventCalendar_EventMember.setCalendar(calendarNew);
            }
            eventCalendar_EventMember = em.merge(eventCalendar_EventMember);
            /*if (calendarOld != null && !calendarOld.equals(calendarNew)) {
                calendarOld.getNewEventCalendar_EventMemberRecord().remove(eventCalendar_EventMember);
                calendarOld = em.merge(calendarOld);
            }
            if (calendarNew != null && !calendarNew.equals(calendarOld)) {
                calendarNew.getNewEventCalendar_EventMemberRecord().add(eventCalendar_EventMember);
                calendarNew = em.merge(calendarNew);
            }*/
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventCalendar_EventMember.getId();
                if (findEventCalendar_EventMember(id) == null) {
                    throw new NonexistentEntityException("The eventCalendar_EventMember with id " + id + " no longer exists.");
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
            EventCalendar_EventMember eventCalendar_EventMember;
            try {
                eventCalendar_EventMember = em.getReference(EventCalendar_EventMember.class, id);
                eventCalendar_EventMember.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventCalendar_EventMember with id " + id + " no longer exists.", enfe);
            }
            EventCalendar calendar = eventCalendar_EventMember.getCalendar();
            /*if (calendar != null) {
                calendar.getNewEventCalendar_EventMemberRecord().remove(eventCalendar_EventMember);
                calendar = em.merge(calendar);
            }*/
            em.remove(eventCalendar_EventMember);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventCalendar_EventMember> findEventCalendar_EventMemberEntities() {
        return findEventCalendar_EventMemberEntities(true, -1, -1);
    }

    public List<EventCalendar_EventMember> findEventCalendar_EventMemberEntities(int maxResults, int firstResult) {
        return findEventCalendar_EventMemberEntities(false, maxResults, firstResult);
    }

    private List<EventCalendar_EventMember> findEventCalendar_EventMemberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventCalendar_EventMember.class));
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

    public EventCalendar_EventMember findEventCalendar_EventMember(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventCalendar_EventMember.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventCalendar_EventMemberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventCalendar_EventMember> rt = cq.from(EventCalendar_EventMember.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Boolean recordExists(Integer calendarId,String userName) {
        EntityManager em = getEntityManager();
        try {        	
        	Query q = em.createQuery("select count(r) from EventCalendar_EventMember r where r.calendar.id = :id and r.member.userName = :n ");
        	q.setParameter("id", calendarId);
        	q.setParameter("n", userName);
            Integer count = ((Long) q.getSingleResult()).intValue();
            return count > 0 ? true : false ;
        } finally {
            em.close();
        }
    }
    
    public List<EventCalendar_EventMember> findRecordsByCalendar(Integer calendarId) {
        EntityManager em = getEntityManager();
        try {        	
        	Query q = em.createQuery("select r from EventCalendar_EventMember r where r.calendar.id = :id ");
        	q.setParameter("id", calendarId);
        	
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Integer deleteRecordsByCalendar(Integer calendarId) {
        EntityManager em = getEntityManager();
        try {     
        	em.getTransaction().begin();
        	Query q = em.createQuery("delete from EventCalendar_EventMember r where r.calendar.id = :id ");
        	q.setParameter("id", calendarId);
        	Integer count = q.executeUpdate();
        	em.getTransaction().commit();
            return count;
        } finally {
            em.close();
        }
    }
    
}
