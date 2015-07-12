<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="xf" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Time Event Manager</a>
		</div>


		<!-- Collect the nav links, forms, and other content for toggling -->


		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<c:when test="${!empty pageContext.request.remoteUser}">
					<li><a href="calendar.jsp">My Calendar</a></li>
					<li><a href="settings.jsp">Settings</a></li>
					<li><a href="SignOut">Sign out</a></li>
				</c:when>
				<c:otherwise>
					<li><xf:ModalDialogToggleText dialogId="dialogSignin"
							text="Signin" /></li>
					<li><xf:ModalDialogToggleText dialogId="dialogSignup"
							text="Signup" /></a></li>
				</c:otherwise>
			</c:choose>


			<li><a href="about.jsp">About</a></li>
		</ul>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

<form  action="j_security_check" method=post id="signin_form">
	<xf:ModalDialog dialogId="dialogSignin" title="Signin">
		<xf:ModalDialogBody>

			<xf:TextField id="jname" label="User Name" placeHolder="User Name" type="text"
				name="j_username" />
			<xf:TextField id="jpassword" label="Password" placeHolder="Password" type="password"
				name="j_password"  />

		</xf:ModalDialogBody>
		<xf:ModalDialogFooter>
			<xf:ModalDialogCloseButton />
			<button type="button" onclick="signin_submit()" class="btn btn-primary">Signin</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>
</form>

<xf:ModalDialog dialogId="dialogSignup" title="Signup">
	<xf:ModalDialogBody>
		<form>
			<xf:TextField id="name" label="Name" placeHolder="Name" type="text" />
			<xf:TextField id="email" label="Email" placeHolder="Email"
				type="email" />
			<xf:TextField id="userName" label="User Name" placeHolder="User Name"
				type="text" />
			<xf:TextField id="password" label="Password" placeHolder="Password"
				type="password" />
			<xf:TextField id="retypePassword" label="Confirm Password"
				placeHolder="Retype Password" type="password" />
		</form>
		<div class="alert" role="alert" id="signup_info" style="display: none" ></div>
	</xf:ModalDialogBody>
	<xf:ModalDialogFooter id="signup_footer">
		<xf:ModalDialogCloseButton onClick="signup_info_hide()"/>
		<button type="button" class="btn btn-primary" onclick="signup()">Sign up</button>
	</xf:ModalDialogFooter>
</xf:ModalDialog>


<script src="./js/navbar.js"></script>




