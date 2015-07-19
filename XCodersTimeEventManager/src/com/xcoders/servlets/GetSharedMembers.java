package com.xcoders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.controller.EventCalendar_EventMemberJpaController;
import com.xcoders.model.EventCalendar_EventMember;

/**
 * Servlet implementation class GetSharedMembers
 */
@WebServlet("/GetSharedMembers")
public class GetSharedMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSharedMembers() {
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
			Integer id = new Integer(request.getParameter("id"));
			// validate
			

			// action
			if (parametersValid) {

				try {
					String user = request.getRemoteUser();
					if (user == null) {
						reply = "Please login!";
					} else {
						EventCalendar_EventMemberJpaController ecem = new EventCalendar_EventMemberJpaController();
						List<EventCalendar_EventMember> records = ecem.findRecordsByCalendar(id);
						for(EventCalendar_EventMember r : records){
							reply += r.getMember().getUserName() + ";";
						}	
						if(reply.length() > 0){
							reply = reply.substring(0,reply.length() - 1);
						}						
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
