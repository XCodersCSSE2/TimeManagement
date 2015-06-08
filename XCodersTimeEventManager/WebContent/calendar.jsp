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

<%@taglib prefix="x" uri="/WEB-INF/tlds/eventtime"%>
<%@taglib prefix="xf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<xf:CSSImports/>
</head>
<body>
	<xf:NavBar />
	<xf:PageHeader subtext="plan your events using the calendar" text="My Calendar" />
<div class="row">
		<div class="col-md-3">
			<x:DHXMiniCalendar />
		</div>
		<div class="col-md-9">
			<x:DHXPlanner theme="terrace" miniCalendar="true" />
		</div>
	</div>
<xf:JSImports/>
</body>
</html>