/* 
 *File info :  Servlet for create an XML file from calendar.
 *File History
 *----------------------------------------------------
 *date		 index	    name	    info
 *----------------------------------------------------
 *20150719  13208221   Ishantha	   created.
 *----------------------------------------------------
 */

package com.xcoders.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.model.Event;
import com.xcoders.model.EventCalendar;

/**
 * Servlet implementation class ExportXML
 */
@WebServlet("/ExportXML")
public class ExportXML extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportXML() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/xml");
		ServletOutputStream out = response.getOutputStream();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			Boolean parametersValid = true;
			// String calendarIdAsString = request.getParameter("calendarIDs");
			// String[] calendarIDs = calendarIdAsString.split(";");

			// ravindu
			Enumeration<String> paramNames = request.getParameterNames();
			List<String> calendarIds = new ArrayList<String>();
			int i = 0;
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement();
				calendarIds.add(request.getParameter(name));
				System.out.println(name + "" + request.getParameter(name));
				i++;
			}
			System.out.println("-----------");
			// ravindu

			// String[] calendarIDs = {"2502"};
			if (parametersValid) {
				try {
					String user = request.getRemoteUser();
					if (user == null) {
					} else {
						Element calendars = new Element("Calendars");
						Document doc = new Document();
						doc.setRootElement(calendars);
						for (String calendarId : calendarIds) {
							EventCalendar eventCalendar;
							EventCalendarJpaController ecjpa = new EventCalendarJpaController();
							eventCalendar = ecjpa.findEventCalendar(Integer.parseInt(calendarId));
							String calendarName = eventCalendar.getName();
							List<Event> listEventData = eventCalendar.getEvents();

							Element calendar = new Element("Calendar");
							calendars.addContent(calendar);

							Element calendar_id = new Element("Id")
									.setText(calendarId);
							Element calendar_name = new Element("Name")
									.setText(calendarName);
							calendar.addContent(calendar_id);
							calendar.addContent(calendar_name);
							/*
							 * doc.getRootElement().addContent(calendar_id);
							 * doc.getRootElement().addContent(calendar_name);
							 */
							 for(Event eventdata : listEventData){ 
								 Element event = new Element("Event");
								 event.addContent(new Element("Id").setText(Integer.toString(eventdata.getId())));
								 event.addContent(new Element("Text").setText(eventdata.getText()));
								 event.addContent(new Element("Location").setText(eventdata.getAddress()));
								 event.addContent(new Element("StartDate").setText(dateFormat.format(eventdata.getStartDate()))); 
								 event.addContent(new Element("EndDate").setText(dateFormat.format(eventdata.getEndDate()))); 
								 event.addContent(new Element("LocationX" ).setText(eventdata.getLocationX()));
								 event.addContent(new Element("LocationY").setText(eventdata.getLocationY())); 
								 event.addContent(new Element("EventLength").setText(Integer.toString(eventdata.getEventLength()))); 
								 event.addContent(new Element("PId").setText(Integer.toString(eventdata.getEventPid()))); 
								 event.addContent(new Element("Record_Type").setText(eventdata.getRecType()));
							 
								 calendar.addContent(event);
						
							 }
						
						}
						XMLOutputter xmlOutput = new XMLOutputter();
						xmlOutput.setFormat(Format.getPrettyFormat());
						xmlOutput.output(doc, out);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			out.close();
		}
	}
}
