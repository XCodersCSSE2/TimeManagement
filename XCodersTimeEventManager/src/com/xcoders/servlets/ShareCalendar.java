package com.xcoders.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.controller.EventCalendar_EventMemberJpaController;
import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventCalendar_EventMember;
import com.xcoders.model.EventMember;

/**
 * Servlet implementation class ShareCalendar
 */
@WebServlet("/ShareCalendar")
public class ShareCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShareCalendar() {
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
			Integer id = new Integer(request.getParameter("id"));
			String list = request.getParameter("list");
			System.out.println(">>>> " + list);
			// validate
			if (list.trim().isEmpty()) {
				reply = "invalid calendar list";
				parametersValid = false;
			}

			String[] userNames = list.split(";");
			// action
			if (parametersValid) {

				try {
					String user = request.getRemoteUser();
					if (user == null) {
						reply = "Please login!";
					} else {
						EventCalendar c = new EventCalendarJpaController()
								.findEventCalendar(id);
						EventMemberJpaController emjc = new EventMemberJpaController();
						EventCalendar_EventMemberJpaController ecemjc = new EventCalendar_EventMemberJpaController();
						ecemjc.deleteRecordsByCalendar(id);
						for (String name : userNames) {
							if (!ecemjc.recordExists(id, name)) {
								EventMember em = emjc.findEventMemberByUserName(name);
								EventCalendar_EventMember ecem = new EventCalendar_EventMember(c, em, true);
								ecemjc.create(ecem);
							}
						}
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
