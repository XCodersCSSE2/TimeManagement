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

import com.xcoders.model.EventMember;

public class TestJPA {

	public static void main(String[] args) {		
		test1();

	}
	
	static void test1(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("XCodersTimeEventManager");
		EntityManager em = emf.createEntityManager();
		
		EventMember member = new EventMember("ravindu","raoffire@yahoo.com","ravindu","abc");
		
		em.getTransaction().begin();
		em.persist(member);
		em.getTransaction().commit();
		
	}

}
