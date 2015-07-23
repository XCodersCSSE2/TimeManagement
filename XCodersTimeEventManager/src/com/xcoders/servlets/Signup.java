/* 
 *File info : Ajax servlet to signup users.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150718  13208316	ravindu		create default settings on signup.
 *20150615  13208221    Ishantha    encrypted password.
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

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.controller.EventJpaController;
import com.xcoders.controller.EventMemberJpaController;
import com.xcoders.controller.SettingJpaController;
import com.xcoders.model.EventCalendar;
import com.xcoders.model.EventMember;
import com.xcoders.model.Setting;
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
		
		
		try{ 
			
			
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
				reply = "Passwords does not match";
				parametersValid = false;
			}else if(name.trim().isEmpty()){
				reply = "Name cannot be empty";
				parametersValid = false;
			}else if(userName.trim().isEmpty()){
				reply = "Username cannot be empty";
				parametersValid = false;
			}else if(password.trim().isEmpty()){
				reply = "Password cannot be empty";
				parametersValid = false;
			}else if(email.trim().isEmpty()){
				reply = "Email cannot be empty";
				parametersValid = false;
			}else if(!(email.contains("@") && email.contains("."))){
				reply = "Invalid Email";
				parametersValid = false;
			}
			
			//(+) 20150615 Ishantha (Start)
			//encrypting user password
			String encryptedPassword;
			encryptedPassword = EncryptPassword.hashPassword(password, "salt");
			//(+) 20150615 Ishantha (End)
			
			//action
			if(parametersValid){
				
				//(+) 20150615 Ishantha (Start)
				EventMember member = new EventMember(name, email, userName, encryptedPassword); 
				// change parameter password as encryptedPassword
				//(+) 20150615 Ishantha (End)
				//(-) 20150615 Ishantha (Start)
				//EventMember member = new EventMember(name, email, userName, password); 
				//(-) 20150615 Ishantha (End)
				try{
					System.out.println("memebr id : " + member.getId());
					new EventMemberJpaController().create(member);
					EventCalendar calendar = new EventCalendar("My Calendar", member);
					new EventCalendarJpaController().create(calendar);
					
					//(+)20150718 ravindu (Start)
					Setting setting1 = new Setting();
					setting1.setName("default-calendar");
					setting1.setValue(calendar.getId().toString());									
					setting1.setMember(member);
					
					Setting setting2 = new Setting();
					setting2.setName("theme_app ");
					setting2.setValue("silver");									
					setting2.setMember(member);
					
					Setting setting3 = new Setting();
					setting3.setName("theme_planner");
					setting3.setValue("terrace");									
					setting3.setMember(member);
					
					SettingJpaController sjc = new SettingJpaController();
					sjc.create(setting1);
					sjc.create(setting2);
					sjc.create(setting3);
					//(+)20150718 ravindu (End)
					reply = "s";
				}catch(Exception e){
					System.out.println("registration error :" + e.getMessage());
					if(e.getMessage().contains("Duplicate entry")){
						reply = "User already registered";
					}else{
						reply = "Error";
					}
				}
									
			}
			
			//reply
			System.out.println("A----------------------" + reply);
			out.print(reply);
		
		//(+) 20150615 Ishantha (Start)
		}catch (NoSuchAlgorithmException e1) {			
			e1.printStackTrace();
		} 
		catch (InvalidKeySpecException e1) {		
			e1.printStackTrace();
		}
		//(+) 20150615 Ishantha (End)
		finally{
			out.close();
		}
		
	}

}
