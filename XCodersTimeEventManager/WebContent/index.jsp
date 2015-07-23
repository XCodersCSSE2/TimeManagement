<%-- 
 File info : index page.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150720  13208367 vijani		page design
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
<title>Time Event Manager</title>
<xf:CSSImports />
</head>
<body style="background-image: url('images/intro-bg.jpg');">
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
	
	<!-- (+) 20150721 vijani (start) -->
	
	<!-- Header -->
	
    <div class="intro-header" style="position: absolute;top: 30%;left:5%" >
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1 align="center" style="color: white;font-family: Lato,Helvetica Neue,Helvetica,Arial,sans-serif;font-weight: 700;font-size: 5em;text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.6);">Time Event Manager</h1>
                        <h3 align="center" style="color: white;font-family: Lato,Helvetica Neue,Helvetica,Arial,sans-serif;font-weight: 700;font-size: 2em;text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.6);">Plan your day</h3>
                        <hr class="intro-divider" style="width: 400px;border-top: 1px solid #F8F8F8;border-bottom: 1px solid rgba(0, 0, 0, 0.2);">
                       
                       <div align ="center" style="padding-bottom: 0px">
                       <span class="glyphicon glyphicon-calendar" style="color: white;font-size: 60px" align="center"></span>
                    <h2 style=" color: white;font-family: Lato,Helvetica Neue,Helvetica,Arial,sans-serif;font-weight: 700;font-size: 2em;text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.6);">Sign Up Today And Start Planning Your Day</h2>
                </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->
    
      <!-- Page Content -->


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    
    
	<!-- (+) 20150721 vijani (end) -->
	
	
	
	
	
</body>
</html>