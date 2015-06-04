<%-- 
 File info : load calendar event json data.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150604  13208316	ravindu		created.
 ----------------------------------------------------
 --%>
<%@ page contentType="application/json" %>
<%= getEvents(request) %>
 
<%@ page import="com.dhtmlx.planner.*,com.xcoders.calendar.*" %>
<%!
           	String getEvents(HttpServletRequest request) throws Exception {
                          	EventsManager evs = new EventsManager(request);
                          	return evs.run();
           	}
%>