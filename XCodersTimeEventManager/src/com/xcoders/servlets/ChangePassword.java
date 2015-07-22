/* 
 *File info : Ajax servlet to change user password.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150722  13208367	vijani    doPost method
 *20150722  13208367	vijani		created.
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
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// (+) 20150722 vijani (start)
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Boolean parametersValid = true;
			String reply = "";

			// get parameters
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String retypeNewPassword = request
					.getParameter("retypeNewPassword");

			// validate
			if (!newPassword.equals(retypeNewPassword)) {
				reply = "New passwords does not match!";
			}

			// action
			System.out.println("1");
			if (parametersValid) {
				String encryptedOldPassword;
				encryptedOldPassword = EncryptPassword.hashPassword(
						oldPassword, "salt");

				String user = request.getRemoteUser();
				if (user == null) {
					reply = "Please login!";
				} else {
					System.out.println(">>> USer : " + user);
					System.out.println("2");
					EventMemberJpaController emj =  new EventMemberJpaController();
					EventMember em = emj
							.findEventMemberByUserName(user);
					String userPassword = em.getPassword();
					if (!encryptedOldPassword.equals(userPassword)) {
						reply = "Your old password is incorrect, please login with correct password";
						// call sign out here 
					}else{
						em.setPassword(EncryptPassword.hashPassword(newPassword, "salt"));
						emj.edit(em);
						System.out.println("3");
						reply = "s";
						//reply = "Your password has changed. Please sign up with your new password!"; 
						// call sign out here 
					}
				}
			}

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		// (+) 20150722 vijani (end)
	}

}
