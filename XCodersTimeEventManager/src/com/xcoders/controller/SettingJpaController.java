/* 
 *File info : JpaController class for Setting.
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

import com.xcoders.model.EventMember;
import com.xcoders.model.Setting;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SettingJpaController implements Serializable {

    public SettingJpaController() {
        this.emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Setting setting) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventMember member = setting.getMember();
            if (member != null) {
                member = em.getReference(member.getClass(), member.getId());
                setting.setMember(member);
            }
            em.persist(setting);
            if (member != null) {
                member.getSettings().add(setting);
                member = em.merge(member);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Setting setting) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Setting persistentSetting = em.find(Setting.class, setting.getId());
            EventMember memberOld = persistentSetting.getMember();
            EventMember memberNew = setting.getMember();
            if (memberNew != null) {
                memberNew = em.getReference(memberNew.getClass(), memberNew.getId());
                setting.setMember(memberNew);
            }
            setting = em.merge(setting);
            if (memberOld != null && !memberOld.equals(memberNew)) {
                memberOld.getSettings().remove(setting);
                memberOld = em.merge(memberOld);
            }
            if (memberNew != null && !memberNew.equals(memberOld)) {
                memberNew.getSettings().add(setting);
                memberNew = em.merge(memberNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = setting.getId();
                if (findSetting(id) == null) {
                    throw new NonexistentEntityException("The setting with id " + id + " no longer exists.");
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
            Setting setting;
            try {
                setting = em.getReference(Setting.class, id);
                setting.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The setting with id " + id + " no longer exists.", enfe);
            }
            EventMember member = setting.getMember();
            if (member != null) {
                member.getSettings().remove(setting);
                member = em.merge(member);
            }
            em.remove(setting);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Setting> findSettingEntities() {
        return findSettingEntities(true, -1, -1);
    }

    public List<Setting> findSettingEntities(int maxResults, int firstResult) {
        return findSettingEntities(false, maxResults, firstResult);
    }

    private List<Setting> findSettingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Setting.class));
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

    public Setting findSetting(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Setting.class, id);
        } finally {
            em.close();
        }
    }

    public int getSettingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Setting> rt = cq.from(Setting.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Setting findSettingByName(String userName,String name){
    	EntityManager em = getEntityManager();
        try {
           Query q = em.createQuery("select s from Setting s where s.member.userName = :uname and s.name = :name");
           q.setParameter("uname",userName);
           q.setParameter("name", name);
                     
           return (Setting)q.getSingleResult();
        } catch(Exception e){
        	return null;
        }finally {
            em.close();
        }
    }
    
}
