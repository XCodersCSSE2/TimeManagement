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
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml");
		ServletOutputStream out =  response.getOutputStream();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
		try { 
			Boolean parametersValid = true;			
			//String calendarIdAsString = request.getParameter("calendarIDs");
			//String[] calendarIDs = calendarIdAsString.split(";");	
			
			String[] calendarIDs = {"2502"};
			if (parametersValid) {
				try {
					String user = request.getRemoteUser();
					if (user == null) {						
					} 
					else {				
						for(String calendarId : calendarIDs){
							EventCalendar eventCalendar;
							EventCalendarJpaController ecjpa = new EventCalendarJpaController();
							eventCalendar = ecjpa.findEventCalendar(Integer.parseInt(calendarId)); 
							String calendarName = eventCalendar.getName();
							List<Event> listEventData = eventCalendar.getEvents();
								
							Element calendar = new Element("Calendar");
							Document doc = new Document();
							doc.setRootElement(calendar);
						
							Element calendar_id = new Element("ID").setText(calendarId);
							Element calendar_name = new Element("Name").setText(calendarName);
							doc.getRootElement().addContent(calendar_id);	
							doc.getRootElement().addContent(calendar_name);			
						
							for(Event eventdata : listEventData){			
								Element event = new Element("Event");
								event.addContent(new Element("Id").setText(Integer.toString(eventdata.getId())));		
								event.addContent(new Element("Text").setText(eventdata.getText()));
								event.addContent(new Element("Location").setText(eventdata.getAddress()));
								event.addContent(new Element("Start_Date").setText(dateFormat.format(eventdata.getStartDate())));
								event.addContent(new Element("End_Date").setText(dateFormat.format(eventdata.getEndDate())));
								event.addContent(new Element("LocationX").setText(eventdata.getLocationX()));
								event.addContent(new Element("LocationY").setText(eventdata.getLocationY()));
								event.addContent(new Element("Event_Length").setText(Integer.toString(eventdata.getEventLength())));
								event.addContent(new Element("PID").setText(Integer.toString(eventdata.getEventPid())));
								event.addContent(new Element("Record_Type").setText(eventdata.getRecType()));
								
								doc.getRootElement().addContent(event);
				        		}    					
							XMLOutputter xmlOutput = new XMLOutputter();	 
							xmlOutput.setFormat(Format.getPrettyFormat());																		         
					        xmlOutput.output(doc, out);					
							}											
					}
				} 
			catch (IOException e) {
				e.printStackTrace();								
				}
			}					
		  } 			
		finally {
			out.close();
		}	
	}
}



