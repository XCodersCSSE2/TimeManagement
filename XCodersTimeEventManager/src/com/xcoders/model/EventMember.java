/* 
 *File info : Entity class to represent User.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;
import javax.xml.ws.BindingType;

/**
 * Entity implementation class for Entity: EventMember
 *
 */
@Entity

public class EventMember implements Serializable {

	private static final long serialVersionUID = 1L;   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(nullable= false)
	private String name;
	@Column(unique=true,nullable=false)
	private String email;
	@Column(unique=true,nullable=false)
	private String userName;
	@Column(nullable=false)
	private String password;
	@OneToMany(mappedBy = "owner",cascade=CascadeType.ALL)
	private List<EventGroup> groups;

	public EventMember() {		
	}   
	
	
	
	public EventMember(String name, String email, String userName,
			String password) {
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
