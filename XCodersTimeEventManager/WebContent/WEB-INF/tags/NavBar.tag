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

<form name=abc action="j_security_check" method=post>
	<xf:ModalDialog dialogId="dialogSignin" title="Signin">
		<xf:ModalDialogBody>

			<xf:TextField id="jname" label="User Name" placeHolder="User Name" type="text"
				name="j_username" />
			<xf:TextField id="jpassword" label="Password" placeHolder="Password" type="password"
				name="j_password" />

		</xf:ModalDialogBody>
		<xf:ModalDialogFooter>
			<xf:ModalDialogCloseButton />
			<button type="submit" class="btn btn-primary">Signin</button>
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


<script>
	function signup() {
		signup_info_show("Please Wait...", "info", true);
				
		var params = {
			name : _("name").value,
			email : _("email").value,
			userName : _("userName").value,
			password : _("password").value,
			retypePassword : _("retypePassword").value
		};
		ajaxPost("Signup", params, signup_callback);

	}

	function signup_callback(response) {		
		
		if (response === "s") {
			$('#dialogSignup').modal('hide');
			signup_info_hide();
		} else {
			signup_info_show(response, "warning", false);
		}
	}
	
	function signup_info_show(message,type,hide_footer){
		_("signup_info").setAttribute("class", "alert alert-" + type);
		_("signup_info").innerHTML = message;
		_("signup_info").style.display = "";
		if(hide_footer){
			_("signup_footer").style.display = "none";
		}else{
			_("signup_footer").style.display = "";
		}
	}
	
	function signup_info_hide(){
		_("signup_info").style.display = "none";
		_("signup_footer").style.display = "";
		_("name").value = "";
		_("email").value = "";
		_("userName").value = "";
		_("password").value = "";
		_("retypePassword").value = "";
	}
</script>




