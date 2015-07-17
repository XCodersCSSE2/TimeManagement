/* 
 *File info : Custom Tag for Calendar control.
 *File History
 *----------------------------------------------------
 *date		index	    name	    info
 *----------------------------------------------------
 *20150715	13208221	Ishantha	map view
 *20150604  13208316	ravindu		created.
 *----------------------------------------------------
 */

package com.xcoders.tags;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.dhtmlx.planner.*;
import com.dhtmlx.planner.controls.DHXMiniCalendar;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.dhtmlx.planner.extensions.DHXExtension;


public class DHXPlanner extends SimpleTagSupport {

	private String theme;
	private boolean miniCalendar;
		
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean getMiniCalendar() {
		return miniCalendar;
	}

	public void setMiniCalendar(boolean miniCalendar) {
		this.miniCalendar = miniCalendar;
	}

	@Override
	public void doTag() throws JspException, IOException {
		DHXSkin skin;
		
		if(theme == null){
			theme = "";
		}else{
			theme = theme.toLowerCase();
		}
		
		switch(theme){
			case "terrace" :
				skin = DHXSkin.TERRACE;
				break;
			case "classic" :
				skin = DHXSkin.CLASSIC;
				break;
			case "glossy" :
				skin = DHXSkin.GLOSSY;
				break;
			default :
				skin = DHXSkin.TERRACE;
				break;
		}
		com.dhtmlx.planner.DHXPlanner s = new com.dhtmlx.planner.DHXPlanner("./codebase/",skin );
		s.setWidth(900);		
		s.setInitialDate(new Date());
		s.load("events.jsp", DHXDataFormat.JSON);
		s.data.dataprocessor.setURL("events.jsp");
		s.extensions.add(DHXExtension.READONLY);
		s.extensions.add(DHXExtension.RECURRING);
		if(miniCalendar){
			DHXMiniCalendar cal = new DHXMiniCalendar("miniCalendar");
			cal.setNavigation(true);
			s.calendars.add(cal);	
		}
		
		try {
			getJspContext().getOut().write("<div class=\"planner\" id=\"planner\" >");
			
			//(+) 20150715 Ishantha (Start)
			getJspContext().getOut().write("<div id='scheduler_here' class='dhx_cal_container' style='width:900px;height:100%;'>");
			getJspContext().getOut().write("<div class='dhx_cal_navline'>");
			getJspContext().getOut().write("<div class='dhx_cal_prev_button'>&nbsp;</div>");
			getJspContext().getOut().write("<div class='dhx_cal_next_button'>&nbsp;</div>");
			getJspContext().getOut().write("<div class='dhx_cal_today_button'></div>");
			getJspContext().getOut().write("<div class='dhx_cal_date'></div>");
			getJspContext().getOut().write("<div class='dhx_cal_tab' name='month_tab' style='left:76px;'></div>");
			getJspContext().getOut().write("<div class='dhx_cal_tab' name='week_tab' style='left:140px;'></div>");
			getJspContext().getOut().write("<div class='dhx_cal_tab' name='day_tab' style='left:204px;'></div>");
			getJspContext().getOut().write("<div class='dhx_cal_tab' name='map_tab' style='left:268px;'></div>");
			getJspContext().getOut().write("</div>");
			getJspContext().getOut().write("<div class='dhx_cal_header'></div>");
			getJspContext().getOut().write("<div class='dhx_cal_data'></div>");
			getJspContext().getOut().write("</div>");
			//(+) 20150715 Ishantha (End)		
			
			getJspContext().getOut().write(s.render());			
			getJspContext().getOut().write("</div>");				
		} catch (Exception e) {
			getJspContext().getOut().write(e.getMessage());
		}		
	}

}
