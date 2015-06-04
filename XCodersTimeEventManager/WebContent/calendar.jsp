<%-- 
 File info : Class Test jpa model.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150604  13208316	ravindu		created.
 ----------------------------------------------------
 --%>
<%@page import="java.util.Date"%>
<%@page import="com.dhtmlx.planner.controls.DHXMapView"%>
<%@page import="com.dhtmlx.planner.controls.DHXMiniCalendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1" style="height: 100%;width:100%">
		<tr>
			<td valign="top" style="width:200px">
				<div id="c1"></div>
			</td>
			<td valign="top" class="planner" id="planner">
				
					<%=getPlanner(request)%>

				
			</td>
		</tr>
	</table>

	<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*"%>
	<%!String getPlanner(HttpServletRequest request) throws Exception {
		
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
		s.setWidth(900);
		s.setInitialDate(new Date());
		s.load("events.jsp", DHXDataFormat.JSON);
		s.data.dataprocessor.setURL("events.jsp");
		DHXMiniCalendar cal = new DHXMiniCalendar("c1");
		cal.setNavigation(true);
		s.calendars.add(cal);
		
		/*DHXWeekAgendaView view = new DHXWeekAgendaView();//initializes the view
		view.setLabel("Week");// the name of the tab
		s.views.add(view);//adds the view to the planner*/
		
		//DHXMapView mv = new DHXMapView();
		//s.views.add(mv);
		
		//s.extensions.add(DHXExtension.RECURRING);
		
		
		return s.render();
	}%>
</body>
</html>