/* 
 *File info : Event handler for calendar control.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20650604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.calendar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventRec;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.controller.EventJpaController;
import com.xcoders.controller.SettingJpaController;
import com.xcoders.model.Event;
import com.xcoders.model.EventCalendar;
import com.xcoders.model.Setting;

public class EventsManager extends DHXEventsManager {
	private HttpServletRequest request;
	
	public EventsManager(HttpServletRequest request) {		
		super(request);
		this.request = request;
	}

	@Override
	public Iterable getEvents() {
		ArrayList<GUIEvent> dEvents = new ArrayList<GUIEvent>();

		List<Event> events = new EventJpaController().findDefaultCalendarEvents(request.getRemoteUser());
		for (Event event : events) {
			GUIEvent eventRec = new GUIEvent();
			eventRec.setId(event.getId());
			eventRec.setText(event.getText());
			eventRec.setStart_date(event.getStartDate());
			eventRec.setEnd_date(event.getEndDate());
			eventRec.setEvent_pid(event.getEventPid());
			eventRec.setEvent_length(event.getEventLength());
			eventRec.setRec_type(event.getRecType());
			dEvents.add(eventRec);
		}

		return dEvents;
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {
		GUIEvent devent = (GUIEvent) event;
		EventJpaController ejp = new EventJpaController();
		Event perEvent;
		System.out.println("devent.getId() ::: " + devent.getId());
		if(status == DHXStatus.INSERT){
			System.out.println("Insert");
			perEvent = new Event();
		}else{
			System.out.println("update");
			perEvent = ejp.findEvent(devent.getId());
		}
		perEvent.setText(devent.getText());
		perEvent.setStartDate(devent.getStart_date());
		perEvent.setEndDate(devent.getEnd_date());
		perEvent.setEventPid(devent.getEvent_pid());
		perEvent.setEventLength(devent.getEvent_length());
		perEvent.setRecType(devent.getRec_type());

		try {
			if (status == DHXStatus.UPDATE) {
				ejp.edit(perEvent);
			} else if (status == DHXStatus.INSERT) {
				Setting calendarSetting = new SettingJpaController().findSettingByName(request.getRemoteUser(), "default-calendar");
				Integer calendarId = new Integer(calendarSetting.getValue());
				EventCalendarJpaController ecjpac = new EventCalendarJpaController();				
				EventCalendar calendar = ecjpac.findEventCalendar(calendarId);
				calendar.getEvents().add(perEvent);
				perEvent.setCalendar(calendar);				
				ejp.create(perEvent);
				ecjpac.edit(calendar);
				event.setId(perEvent.getId());
			} else if (status == DHXStatus.DELETE) {
				ejp.destroy(perEvent.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.saveEvent(event, status);
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new GUIEvent();
	}

}