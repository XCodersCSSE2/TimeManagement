/* 
 *File info : Ajax servlet to edit a calendar.
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

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventMember;

/**
 * Servlet implementation class EditCalendar
 */
@WebServlet("/EditCalendar")
public class EditCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCalendar() {
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
			String name = request.getParameter("name");
			Integer id = new Integer(request.getParameter("id"));
			// validate
			if (name.trim().isEmpty()) {
				reply = "Please enter a calendar name!";
				parametersValid = false;
			}

			// action
			if (parametersValid) {

				try {
					String user = request.getRemoteUser();
					if (user == null) {
						reply = "Please login!";
					} else {
						EventCalendarJpaController ecj = new EventCalendarJpaController();
						EventCalendar calendar = ecj.findEventCalendar(id);
						calendar.setName(name);
						ecj.edit(calendar);
						reply = "s";
					}
				} catch (Exception e) {
					e.printStackTrace();
					reply = "Error";
				}

			}
		
			// reply
			out.print(reply);
		}finally {
			out.close();
		}
	}

}
