/* 
 *File info : Ajax servlet to sign out users.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20650613  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Signout
 */
@WebServlet(name = "SignOut", urlPatterns = { "/SignOut" })
public class Signout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		response.sendRedirect("index.jsp");
	}

}
