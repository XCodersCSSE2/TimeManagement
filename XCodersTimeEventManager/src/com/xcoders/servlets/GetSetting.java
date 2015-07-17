/* 
 *File info : Ajax servlet to get user settings.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150717  13208316	ravindu		created.
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
import com.xcoders.controller.SettingJpaController;
import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventMember;
import com.xcoders.model.Setting;

/**
 * Servlet implementation class GetSetting
 */
@WebServlet("/GetSetting")
public class GetSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
						SettingJpaController sjpa = new SettingJpaController();
						Setting setting = sjpa.findSettingByName(user, name);
						if(setting != null){
							reply = setting.getValue();
						}else{
							setting = null;
						}
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
