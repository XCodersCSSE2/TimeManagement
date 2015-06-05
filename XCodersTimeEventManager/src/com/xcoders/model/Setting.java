/* 
 *File info : Entity class to represent User Settings.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */
package com.xcoders.model;

import com.xcoders.model.EventMember;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Setting
 *
 */
@Entity

public class Setting implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String value;
	@ManyToOne(cascade=CascadeType.REFRESH)
	private EventMember member;
	

	public Setting() {
		super();
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
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}   
	public EventMember getMember() {
		return this.member;
	}

	public void setMember(EventMember member) {
		this.member = member;
	}
   
}
