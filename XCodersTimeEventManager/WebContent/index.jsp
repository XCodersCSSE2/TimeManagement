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
<%@taglib prefix="x" uri="/WEB-INF/tlds/eventtime"%>
<%@taglib prefix="xf" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<xf:CSSImports />
</head>
<body>
	<xf:NavBar />
	<c:choose>
		<c:when test="${param.login eq 'r'}">
			<!--  <div class="alert alert-info" role="alert">Please sign in to
				continue!</div>-->
		</c:when>
		<c:when test="${param.login eq 'fail'}">
			<div class="alert alert-warning" role="alert">Login Failed!</div>
		</c:when>
	</c:choose>

	<xf:JSImports />
</body>
</html>