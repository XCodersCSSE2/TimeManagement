/* 
 *File info : Ajax servlet to create a new calendar.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20650613  13208316	ravindu		created.
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
 * Servlet implementation class CreateCalendar
 */
@WebServlet("/CreateCalendar")
public class CreateCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCalendar() {
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
		try {
			Boolean parametersValid = true;
			String reply = "";

			// get parameters
			String name = request.getParameter("name");

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
						EventMember em = new EventMemberJpaController()
								.findEventMemberByUserName(user);
						EventCalendar calendar = new EventCalendar(name, em);
						new EventCalendarJpaController().create(calendar);
						reply = "s";
					}
				} catch (Exception e) {
					e.printStackTrace();
					reply = "Error";
				}

			}

			// reply
			out.print(reply);
		} finally {
			out.close();
		}
	}

}
