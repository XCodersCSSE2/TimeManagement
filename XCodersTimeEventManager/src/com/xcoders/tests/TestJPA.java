/* 
 *File info : Class Test jpa model.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventCalendar_EventMember;
import com.xcoders.model.EventMember;

public class TestJPA {

	public static void main(String[] args) {		
		test1();

	}
	
	static void test1(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
		EntityManager em = emf.createEntityManager();
		
		EventMember member1 = new EventMember("ravindu","raoffire@yahoo.com","ravindu","abc");
		EventMember member2 = new EventMember("lasith","123@yahoo.com","lasith","abc");
		EventMember member3 = new EventMember("kaluarachchi","1234@yahoo.com","kalu","abc");
		
		EventCalendar calendar = member1.createCalendar("My Calendar");
		EventCalendar_EventMember calendar_member1  = calendar.getNewEventCalendar_EventMemberRecord(member2, true);
		EventCalendar_EventMember calendar_member2  = calendar.getNewEventCalendar_EventMemberRecord(member3, true);
		
		em.getTransaction().begin();
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(calendar_member1);
		em.persist(calendar_member2);
		em.getTransaction().commit();
		
	}

}
