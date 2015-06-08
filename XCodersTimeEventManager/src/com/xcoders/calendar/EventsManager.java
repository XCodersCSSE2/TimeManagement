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
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.xcoders.controller.EventJpaController;
import com.xcoders.model.Event;

public class EventsManager extends DHXEventsManager {

	public EventsManager(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Iterable getEvents() {
		ArrayList<DHXEvent> dEvents = new ArrayList<DHXEvent>();

		List<Event> events = new EventJpaController().findEventEntities();
		for (Event event : events) {
			dEvents.add(new DHXEvent(event.getId(), event.getStartDate(), event
					.getEndDate(), event.getText()));
		}

		return dEvents;
	}

	@Override
	public DHXStatus saveEvent(DHXEv event, DHXStatus status) {

		Event perEvent = new Event(event.getId(), event.getStart_date(),
				event.getEnd_date(), event.getText(), null);
		try {
			if (status == DHXStatus.UPDATE) {
				new EventJpaController().edit(perEvent);
			} else if (status == DHXStatus.INSERT) {
				new EventJpaController().create(perEvent);
				event.setId(perEvent.getId());
			} else if (status == DHXStatus.DELETE) {
				new EventJpaController().destroy(perEvent.getId());
			}
		} catch (Exception e) {

		}
		return super.saveEvent(event, status);
	}

	@Override
	public DHXEv createEvent(String id, DHXStatus status) {
		return new DHXEvent();
	}

}