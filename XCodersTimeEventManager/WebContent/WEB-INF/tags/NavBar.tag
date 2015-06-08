<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="xf" tagdir="/WEB-INF/tags"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp">Time Event Manager</a>
    </div>


<!-- Collect the nav links, forms, and other content for toggling -->
  
    
      <ul class="nav navbar-nav navbar-right">
        <li><a href="calendar.jsp">My Calendar</a></li>
        <li><a href="settings.jsp">Settings</a></li>
        <li><xf:ModalDialogToggleText dialogId="dialogSignin" text="Signin" /></li>
        <li><xf:ModalDialogToggleText dialogId="dialogSignup" text="Signup" /></a></li>
        <li><a href="about.jsp">About</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<xf:ModalDialog dialogId="dialogSignin" title="Signin">
		<xf:ModalDialogBody>
			<h1>Signin</h1>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter>
			<xf:ModalDialogCloseButton/>
			<button type="button" class="btn btn-primary">Signin</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>

<xf:ModalDialog dialogId="dialogSignup" title="signup">
		<xf:ModalDialogBody>
			<h1>Signup</h1>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter>
			<xf:ModalDialogCloseButton/>
			<button type="button" class="btn btn-primary">Signup</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>





