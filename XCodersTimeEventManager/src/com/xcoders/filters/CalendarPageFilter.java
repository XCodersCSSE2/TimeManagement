package com.xcoders.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.xcoders.controller.EventCalendarJpaController;
import com.xcoders.model.EventCalendar;

/**
 * Servlet Filter implementation class CalendarPageFilter
 */
public class CalendarPageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CalendarPageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		if(req.getRemoteUser() != null ){
			EventCalendarJpaController calendarJpaController = new EventCalendarJpaController();
			List<EventCalendar> calendars = calendarJpaController.findCalendarByMember(req.getRemoteUser());
			request.setAttribute("calendarList", calendars);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
