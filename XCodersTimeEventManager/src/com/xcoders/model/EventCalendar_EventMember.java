/* 
 *File info : Intermediate Entity to represent the 
 *			  many to many mapping between EventCalendar
 *			  and EventMember.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.model;

import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventMember;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EventCalendar_EventMember
 *
 */
@Entity

public class EventCalendar_EventMember implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToOne(cascade=CascadeType.MERGE)
	private EventCalendar calendar;
	@OneToOne(cascade=CascadeType.MERGE)
	private EventMember member;
	private Boolean readOnly;   
		
	public EventCalendar_EventMember() {
	}   
	
	
	public EventCalendar_EventMember(EventCalendar calendar,
			EventMember member, Boolean readOnly) {
		this.calendar = calendar;
		this.member = member;
		this.readOnly = readOnly;
	}


	public EventCalendar getCalendar() {
		return this.calendar;
	}

	public void setCalendarId(EventCalendar calendar) {
		this.calendar = calendar;
	}   
	public EventMember getMember() {
		return this.member;
	}

	public void setMember(EventMember member) {
		this.member = member;
	}   
	public Boolean getReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public void setCalendar(EventCalendar calendar) {
		this.calendar = calendar;
	}
   	
}
