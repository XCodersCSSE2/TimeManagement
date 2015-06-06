<%-- 
 File info : index page.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150604  13208316	ravindu		created.
 ----------------------------------------------------
 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="x" uri="/WEB-INF/tlds/eventtime" %>
<!DOCTYPE >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
	<a href="calendar.jsp" >Calendar Page</a> <br/><br/><br/>
	
	<div class="row">
  <div class="col-md-3"><x:DHXMiniCalendar/></div>
  <div class="col-md-9"><x:DHXPlanner theme="terrace" miniCalendar="true"/></div>
</div>
	
	
	
	<script src="./bootstrap/js/bootstrap.min.js"></script>
	
</body>
</html>