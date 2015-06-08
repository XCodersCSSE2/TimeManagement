/* 
 *File info : JpaController class for EventMember.
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
import com.xcoders.model.EventMember;

import java.util.ArrayList;
import java.util.List;

import com.xcoders.model.Setting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EventMemberJpaController implements Serializable {

    public EventMemberJpaController() {
        this.emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventMember eventMember) {
        if (eventMember.getCalendars() == null) {
            eventMember.setCalendars(new ArrayList<EventCalendar>());
        }
        if (eventMember.getSettings() == null) {
            eventMember.setSettings(new ArrayList<Setting>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EventCalendar> attachedCalendars = new ArrayList<EventCalendar>();
            for (EventCalendar calendarsEventCalendarToAttach : eventMember.getCalendars()) {
                calendarsEventCalendarToAttach = em.getReference(calendarsEventCalendarToAttach.getClass(), calendarsEventCalendarToAttach.getId());
                attachedCalendars.add(calendarsEventCalendarToAttach);
            }
            eventMember.setCalendars(attachedCalendars);
            List<Setting> attachedSettings = new ArrayList<Setting>();
            for (Setting settingsSettingToAttach : eventMember.getSettings()) {
                settingsSettingToAttach = em.getReference(settingsSettingToAttach.getClass(), settingsSettingToAttach.getId());
                attachedSettings.add(settingsSettingToAttach);
            }
            eventMember.setSettings(attachedSettings);
            em.persist(eventMember);
            for (EventCalendar calendarsEventCalendar : eventMember.getCalendars()) {
                EventMember oldOwnerOfCalendarsEventCalendar = calendarsEventCalendar.getOwner();
                calendarsEventCalendar.setOwner(eventMember);
                calendarsEventCalendar = em.merge(calendarsEventCalendar);
                if (oldOwnerOfCalendarsEventCalendar != null) {
                    oldOwnerOfCalendarsEventCalendar.getCalendars().remove(calendarsEventCalendar);
                    oldOwnerOfCalendarsEventCalendar = em.merge(oldOwnerOfCalendarsEventCalendar);
                }
            }
            for (Setting settingsSetting : eventMember.getSettings()) {
                EventMember oldMemberOfSettingsSetting = settingsSetting.getMember();
                settingsSetting.setMember(eventMember);
                settingsSetting = em.merge(settingsSetting);
                if (oldMemberOfSettingsSetting != null) {
                    oldMemberOfSettingsSetting.getSettings().remove(settingsSetting);
                    oldMemberOfSettingsSetting = em.merge(oldMemberOfSettingsSetting);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventMember eventMember) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventMember persistentEventMember = em.find(EventMember.class, eventMember.getId());
            List<EventCalendar> calendarsOld = persistentEventMember.getCalendars();
            List<EventCalendar> calendarsNew = eventMember.getCalendars();
            List<Setting> settingsOld = persistentEventMember.getSettings();
            List<Setting> settingsNew = eventMember.getSettings();
            List<EventCalendar> attachedCalendarsNew = new ArrayList<EventCalendar>();
            for (EventCalendar calendarsNewEventCalendarToAttach : calendarsNew) {
                calendarsNewEventCalendarToAttach = em.getReference(calendarsNewEventCalendarToAttach.getClass(), calendarsNewEventCalendarToAttach.getId());
                attachedCalendarsNew.add(calendarsNewEventCalendarToAttach);
            }
            calendarsNew = attachedCalendarsNew;
            eventMember.setCalendars(calendarsNew);
            List<Setting> attachedSettingsNew = new ArrayList<Setting>();
            for (Setting settingsNewSettingToAttach : settingsNew) {
                settingsNewSettingToAttach = em.getReference(settingsNewSettingToAttach.getClass(), settingsNewSettingToAttach.getId());
                attachedSettingsNew.add(settingsNewSettingToAttach);
            }
            settingsNew = attachedSettingsNew;
            eventMember.setSettings(settingsNew);
            eventMember = em.merge(eventMember);
            for (EventCalendar calendarsOldEventCalendar : calendarsOld) {
                if (!calendarsNew.contains(calendarsOldEventCalendar)) {
                    calendarsOldEventCalendar.setOwner(null);
                    calendarsOldEventCalendar = em.merge(calendarsOldEventCalendar);
                }
            }
            for (EventCalendar calendarsNewEventCalendar : calendarsNew) {
                if (!calendarsOld.contains(calendarsNewEventCalendar)) {
                    EventMember oldOwnerOfCalendarsNewEventCalendar = calendarsNewEventCalendar.getOwner();
                    calendarsNewEventCalendar.setOwner(eventMember);
                    calendarsNewEventCalendar = em.merge(calendarsNewEventCalendar);
                    if (oldOwnerOfCalendarsNewEventCalendar != null && !oldOwnerOfCalendarsNewEventCalendar.equals(eventMember)) {
                        oldOwnerOfCalendarsNewEventCalendar.getCalendars().remove(calendarsNewEventCalendar);
                        oldOwnerOfCalendarsNewEventCalendar = em.merge(oldOwnerOfCalendarsNewEventCalendar);
                    }
                }
            }
            for (Setting settingsOldSetting : settingsOld) {
                if (!settingsNew.contains(settingsOldSetting)) {
                    settingsOldSetting.setMember(null);
                    settingsOldSetting = em.merge(settingsOldSetting);
                }
            }
            for (Setting settingsNewSetting : settingsNew) {
                if (!settingsOld.contains(settingsNewSetting)) {
                    EventMember oldMemberOfSettingsNewSetting = settingsNewSetting.getMember();
                    settingsNewSetting.setMember(eventMember);
                    settingsNewSetting = em.merge(settingsNewSetting);
                    if (oldMemberOfSettingsNewSetting != null && !oldMemberOfSettingsNewSetting.equals(eventMember)) {
                        oldMemberOfSettingsNewSetting.getSettings().remove(settingsNewSetting);
                        oldMemberOfSettingsNewSetting = em.merge(oldMemberOfSettingsNewSetting);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eventMember.getId();
                if (findEventMember(id) == null) {
                    throw new NonexistentEntityException("The eventMember with id " + id + " no longer exists.");
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
            EventMember eventMember;
            try {
                eventMember = em.getReference(EventMember.class, id);
                eventMember.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventMember with id " + id + " no longer exists.", enfe);
            }
            List<EventCalendar> calendars = eventMember.getCalendars();
            for (EventCalendar calendarsEventCalendar : calendars) {
                calendarsEventCalendar.setOwner(null);
                calendarsEventCalendar = em.merge(calendarsEventCalendar);
            }
            List<Setting> settings = eventMember.getSettings();
            for (Setting settingsSetting : settings) {
                settingsSetting.setMember(null);
                settingsSetting = em.merge(settingsSetting);
            }
            em.remove(eventMember);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventMember> findEventMemberEntities() {
        return findEventMemberEntities(true, -1, -1);
    }

    public List<EventMember> findEventMemberEntities(int maxResults, int firstResult) {
        return findEventMemberEntities(false, maxResults, firstResult);
    }

    private List<EventMember> findEventMemberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventMember.class));
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

    public EventMember findEventMember(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventMember.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventMemberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventMember> rt = cq.from(EventMember.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
