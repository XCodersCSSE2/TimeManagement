/* 
 *File info : Entity class to represent Group.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EventGroup
 *
 */
@Entity

public class EventGroup implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	@ManyToOne(cascade=CascadeType.MERGE)
	private EventMember owner;
	@OneToMany(cascade=CascadeType.MERGE)
	private List<EventMember> members;
	

	public EventGroup() {		
	}   
	
	public EventGroup(String name, EventMember owner) {
		this.name = name;
		this.owner = owner;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
