/* 
 *File info : Entity class to represent calendar event.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.model;

import com.xcoders.model.EventCalendar;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	private String text;
	@ManyToOne(cascade=CascadeType.MERGE)
	private EventCalendar calendar;
	

	public Event() {
	}   
	
	public Event(Date startDate, Date endDate, String text,
			EventCalendar calendar) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.text = text;
		this.calendar = calendar;
	}
	
	

	public Event(Integer id, Date startDate, Date endDate, String text,
			EventCalendar calendar) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.text = text;
		this.calendar = calendar;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}   
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public EventCalendar getCalendar() {
		return this.calendar;
	}

	public void setCalendar(EventCalendar calendar) {
		this.calendar = calendar;
	}
   
}
