package com.xcoders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.model.EventMember;

/**
 * Servlet implementation class UserNameMatch
 */
@WebServlet("/UserNameMatch")
public class UserNameMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNameMatch() {
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
			Integer limit = new Integer(request.getParameter("limit"));
			
			// validate
			if (name.trim().isEmpty()) {
				reply = "invalid username";
				parametersValid = false;
			}

			// action
			if (parametersValid) {

				try {
					String user = request.getRemoteUser();
					if (user == null) {
						reply = "Please login!";
					} else {
						try{
							List<EventMember> members = new EventMemberJpaController().findEventMemberByUserNameLike(name,limit);
							for(EventMember m : members){
								reply += m.getUserName() + ";";
							}
							reply = reply.substring(0, reply.length() - 1);
						}catch(NoResultException ex){
							reply = "n";
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
