/* 
 *File info : Ajax hash password.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150613  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.model.EventMember;
import com.xcoders.util.EncryptPassword;

/**
 * Servlet implementation class Encrypt
 */
@WebServlet("/Encrypt")
public class Encrypt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Encrypt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		try{ 
			//get parameters
			String password = request.getParameter("password");
	
		
			String encryptedPassword = EncryptPassword.hashPassword(password, "salt");
			out.print(encryptedPassword);
		}
		catch (NoSuchAlgorithmException e1) {			
			e1.printStackTrace();
		} 
		catch (InvalidKeySpecException e1) {		
			e1.printStackTrace();
		}
		finally{
			out.close();
		}
	}

}
