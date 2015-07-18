<%-- 
 File info : about page.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150604  13208316	ravindu		created.
 20150713  13208367 vijani		added page content.
 ----------------------------------------------------
 --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="x" uri="/WEB-INF/tlds/eventtime"%>
<%@taglib prefix="xf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<xf:CSSImports />
</head>
<body>
	<xf:NavBar />

<!-- (-) Vijani (start) -->
<!-- <h1 align="center"  style="padding-top: 100px;font-size: 100px">XCoders</h1>-->
<!-- (-) Vijani (end) -->

<!--   (+) Vijani (start) -->
<center><img src = "images/Xcoders_logo.jpg"></center>
<!--   (+) Vijani (end) -->

	<xf:JSImports />
	
	
	<!--   (+) Vijani (start) -->
	
	<div class = "container">
	<table align="center"><tr><td>
		<hr class = "featurette-divider">
			<div class = "featurette" id = "about">
				<img class = "featurette-image img-circle img-responsive pull-right" src="images/ravindu.jpg"/>
				<h2 class = "featurette-heading"> Ravindu Kaluarachchi
					<span class = "text-muted">13208316</span>
				</h2>
			</div>
			</td></tr><tr><td>
		<hr class = "featurette-divider">
			<div class = "featurette" id = "about2">
				<img class = "featurette-image img-circle img-responsive pull-right" src="images/vijani.jpg">
				<h2 class = "featurette-heading"> Vijani Piyawardana
					<span class = "text-muted">13208367</span>
				</h2>
			</div>
			</td></tr><tr><td>
		<hr class = "featurette-divider">
			<div class = "featurette" id = "about3">
				<img class = "featurette-image img-circle img-responsive pull-right" src="images/isuri.jpg">
				<h2 class = "featurette-heading"> Isuri Wijesena
					<span class = "text-muted">13208314</span>
				</h2>
			</div></td></tr><tr><td>
		<hr class = "featurette-divider">
			<div class = "featurette" id = "about4">
				<img class = "featurette-image img-circle img-responsive pull-right" src="images/chamini.jpg">
				<h2 class = "featurette-heading"> Chamini Theersha
					<span class = "text-muted">13208330</span>
				</h2>
			</div></td></tr><tr><td>
		<hr class = "featurette-divider">
			<div class = "featurette" id = "about5">
				<img class = "featurette-image img-circle img-responsive pull-right" src="images/ishantha.jpg">
				<h2 class = "featurette-heading"> Ishantha Priyamal
					<span class = "text-muted">13208221</span>
				</h2>
			</div></td></tr></table>	
			
			<!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Xcoders Time Event Manager 2015</p>
                </div>
            </div>
        </footer>	
	</div>
	
	
	<!--   (+) Vijani (end)   -->
</body>
</html>