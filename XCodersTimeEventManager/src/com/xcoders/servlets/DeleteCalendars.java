/* 
 *File info : Ajax servlet to delete a calendar.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150712  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.model.EventCalendar;

/**
 * Servlet implementation class DeleteCalendar
 */
@WebServlet("/DeleteCalendars")
public class DeleteCalendars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCalendars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Boolean parametersValid = true;
			String reply = "";

			// get parameters
			String idString = request.getParameter("ids");
			String[] ids = idString.split(";");
			// validate
			

			// action
			if (parametersValid) {

				try {
					String user = request.getRemoteUser();
					if (user == null) {
						reply = "Please login!";
					} else {
						for(String id : ids){
							EventCalendarJpaController ecj = new EventCalendarJpaController();
							ecj.destroy(Integer.parseInt(id));		
						}
						reply = "s";
					}
				}catch (Exception e) {
					if(e.getMessage().contains("foreign key constraint fails")) {
						reply = "Cannot delete as the calendar is shared with other users";
					}else{
						e.printStackTrace();
						reply = "Error";
					}
				}

			}
		
			// reply
			out.print(reply);
		}finally {
			out.close();
		}
	}

}
