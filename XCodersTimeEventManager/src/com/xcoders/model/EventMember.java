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
import java.util.ArrayList;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<EventGroup> groups;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<EventCalendar> calendars;
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Setting> settings;
	
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

	public List<EventGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<EventGroup> groups) {
		this.groups = groups;
	}

	public List<EventCalendar> getCalendars() {
		return calendars;
	}

	public void setCalendars(List<EventCalendar> calendars) {
		this.calendars = calendars;
	}

	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

	public EventCalendar createCalendar(String name){
		if(calendars == null){
			calendars = new ArrayList<EventCalendar>();
		}		
		EventCalendar eventCalendar = new EventCalendar(name, this);
		calendars.add(eventCalendar);
		return eventCalendar;
	}
	
	public EventGroup createGroup(String name){
		if(groups == null){
			groups = new ArrayList<EventGroup>();
		}		
		EventGroup eventGroup = new EventGroup(name, this);
		groups.add(eventGroup);
		return eventGroup;
	}
	
	public String getSettingValue(String name){
		if(settings == null){
			return null;
		}else{
			for(Setting s : settings){
				if(s.getName().equals("name")){
					return s.getValue();
				}
			}
			return null;
		}
		
	}
	
	public Setting getSetting(String name){
		if(settings == null){
			return null;
		}else{
			for(Setting s : settings){
				if(s.getName().equals("name")){
					return s;
				}
			}
			return null;
		}
		
	}
}
