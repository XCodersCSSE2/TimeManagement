/* 
 *File info : Ajax servlet to signup users.
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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xcoders.controller.EventJpaController;
import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.model.EventMember;

//(+) 20150615 Ishantha (Start)
import com.xcoders.util.*; // for use encrypt function in EncryptPassword class
//(+) 20150615 Ishantha (End)

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//(+) 20150615 Ishantha (Start)
		try{ // for call hash function
			//(+) 20150615 Ishantha (End)
			
			Boolean parametersValid = true;
			String reply = "";
			
			//get parameters
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String retypePassword = request.getParameter("retypePassword");
			
			//validate
			if(!password.equals(retypePassword)){
				reply = "Passwords does not match!";
			}
			
			//(+) 20150615 Ishantha (Start)
			// encrypting user password
			String encryptedPassword;
			encryptedPassword = EncryptPassword.hashPassword(password, "salt");
			//(+) 20150615 Ishantha (End)
			
			//action
			if(parametersValid){
				
				//(+) 20150615 Ishantha (Start)
				EventMember member = new EventMember(name, email, userName, encryptedPassword); 
				// change parameter password as encryptedPassword
				//(+) 20150615 Ishantha (End)
				
				try{
					new EventMemberJpaController().create(member);
					reply = "s";
				}catch(Exception e){
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + e.getMessage());
					reply = "Error";
				}
									
			}
			
			//reply
			System.out.println("A----------------------" + reply);
			out.print(reply);
		
			//(+) 20150615 Ishantha (Start)
		}// end try block use for call hash function 
		catch (NoSuchAlgorithmException e1) {			
			e1.printStackTrace();
		} 
		catch (InvalidKeySpecException e1) {		
			e1.printStackTrace();
		}
		finally{
			out.close();
		}
		//(+) 20150615 Ishantha (End)
	}

}
