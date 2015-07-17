/* 
 *File info : Class to represent a calendar event on gui.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150613  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.calendar;



import com.dhtmlx.planner.DHXEventRec;
import com.xcoders.model.EventCalendar;

public class GUIEvent extends DHXEventRec {
	private EventCalendar calendar;

	public EventCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(EventCalendar calendar) {
		this.calendar = calendar;
	}	
	
}
