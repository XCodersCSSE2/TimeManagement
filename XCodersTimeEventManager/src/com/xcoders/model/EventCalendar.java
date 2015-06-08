/* 
 *File info : Entity class to represent a Calendar.
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EventCalendar
 *
 */
@Entity
public class EventCalendar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@ManyToOne(cascade = CascadeType.MERGE)
	private EventMember owner;
	@OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
	private List<Event> events;

	public EventCalendar() {
	}

	public EventCalendar(String name, EventMember owner) {
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

	public EventMember getOwner() {
		return owner;
	}

	public void setOwner(EventMember owner) {
		this.owner = owner;
	}

	public EventCalendar_EventMember getNewEventCalendar_EventMemberRecord(
			EventMember member, Boolean readOnly) {
		return new EventCalendar_EventMember(this, member, readOnly);
	}
	
	public Event createEvent(Date startDate,Date endDate,String text){
		if(events == null){
			events = new ArrayList<Event>();
		}
		Event event = new Event(startDate, endDate, text, this);
		return event;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
