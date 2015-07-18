/* 
 *File info : Class to represent a calendar event on gui.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150718  13208221    Ishantha    save & retrieve location in db.
 *20150613  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.calendar;



import com.dhtmlx.planner.DHXEventRec;
import com.xcoders.model.EventCalendar;

public class GUIEvent extends DHXEventRec {
	private EventCalendar calendar;
	
	//(+) 20150718 Ishantha (Start) 
	public String event_location; 
	public String lat;
	public String lng;
	//(+) 20150718 Ishantha (End)
	
	public EventCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(EventCalendar calendar) {
		this.calendar = calendar;
	}	
	
	//(+) 20150718 Ishantha (Start)
	public String getEvent_location() {		
		return event_location;
	}
	
	public void setEvent_location(String event_location) {	
		this.event_location = event_location;
	}
	
	public String getLat() {		
		return lat;
	}
	
	public void setLat(String lat) {	
		this.lat = lat;
	}	
	
	public String getLng() {		
		return lng;
	}
	
	public void setLng(String lng) {	
		this.lng = lng;
	}	
	//(+) 20150718 Ishantha (End)
}
