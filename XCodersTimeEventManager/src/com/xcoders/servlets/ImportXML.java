/* 
 *File info :  Servlet for read XML file and create calendar from XML file.
 *File History
 *----------------------------------------------------
 *date		 index	    name	    info
 *----------------------------------------------------
 *20150720  13208221   Ishantha	   created.
 *----------------------------------------------------
 */

package com.xcoders.servlets;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;  
import java.io.IOException;  
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;  

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.controller.EventJpaController;
import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.model.Event;
import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventMember;

/**
 * Servlet implementation class ImportXML
 */
@WebServlet("/ImportXML")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class ImportXML extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";
    String fileName;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
    public ImportXML() {
        super();
        // TODO Auto-generated constructor stub
    }
  
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
		String user = request.getRemoteUser();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
        String appPath = request.getServletContext().getRealPath("");        
        String savePath = appPath + File.separator + SAVE_DIR;
             
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }        
        for (Part part : request.getParts()) {
             fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
        }     
        try {				  			  
			  SAXBuilder builder = new SAXBuilder();
			  File xmlCalendarFile = new File(savePath + File.separator + fileName);			
			  
			  Document document = (Document) builder.build(xmlCalendarFile);
			  Element rootNodeCalendar = document.getRootElement();		 
			  		  	  		 			
			  List calendarName = rootNodeCalendar.getChildren("Name");
			  Element nodeCalendarName = (Element) calendarName.get(0);			
			  String calendar_Name = nodeCalendarName.getText();				  
			  	
			  EventMember eventMember = 
					  new EventMemberJpaController().findEventMemberByUserName(user);	
			  EventCalendar eventCalendar = new EventCalendar(calendar_Name,eventMember); 
			  EventCalendarJpaController exjpa = new EventCalendarJpaController();	 
			  exjpa.create(eventCalendar);
			  
			  List events = rootNodeCalendar.getChildren("Event");			  			  		  
			  for (int i = 0; i < events.size(); i++) {				
				  Element node = (Element) events.get(i); 
				  Event eventdata = new Event();
				  
				 // eventdata.setId(Integer.parseInt(node.getChildText("ID")));
				  eventdata.setText(node.getChildText("Text"));
				  eventdata.setAddress(node.getChildText("Location"));
				  eventdata.setStartDate(dateFormat.parse(node.getChildText("Start_Date")));
				  eventdata.setEndDate(dateFormat.parse(node.getChildText("End_Date")));			  
				  eventdata.setLocationX(node.getChildText("LocationX"));	
				  eventdata.setLocationY( node.getChildText("LocationY"));	
				  eventdata.setEventLength(Integer.parseInt(node.getChildText("Event_Length")));	
				  eventdata.setEventPid(Integer.parseInt(node.getChildText("PID")));	
				  eventdata.setRecType(node.getChildText("Record_Type"));
				  
				  eventdata.setCalendar(eventCalendar);
				  EventJpaController ejpc = new EventJpaController();		
				  ejpc.create(eventdata);
			  }				
		  }
		  catch (IOException io) {
			  System.out.println(io.getMessage());
		  } 
		  catch (JDOMException jdomex) {
			  System.out.println(jdomex.getMessage());
		  } catch (ParseException e) {
			  System.out.println("Date format error");
			  e.printStackTrace();
		  }      
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/calendar.jsp").forward(request, response);
	}     
       
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}