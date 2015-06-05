package com.xcoders.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DHXMiniCalendar extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		try {
			getJspContext().getOut().write("<div id=\"miniCalendar\" ></div>");
		} catch (Exception e) {
			getJspContext().getOut().write(e.getMessage());
		}		
	}
	
}
