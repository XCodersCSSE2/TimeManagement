<%-- 
 File info : settings page.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150722	13208367	vijani	added new tab to user account settings
 20150604  13208316	ravindu		created.
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
<title>Settings</title>
<xf:CSSImports />
</head>
<body>
	<xf:NavBar />
	<xf:PageHeader subtext="use this page to edit your settings" text="Settings" />
	<xf:TabPanel>
		<xf:TabList>
			<xf:TabName name="Theme" active="true"/>
				<!-- (+) vijani (start) -->
			<xf:TabName name="Account" />	
				<!-- (+) vijani (end) -->		
		</xf:TabList>
	</xf:TabPanel>
		<xf:TabBody>
			<xf:Tab name="Theme" active="true">
				<form style="padding: 20px">
					<xf:Dropdown id="app_theme" label="Application Theme" value="${theme_app}" list="${appThemes}" width="200px"/>
					<xf:Dropdown id="planner_theme" label="Planner theme" value="${theme_planner}" list="${plannerThemes}"  width="200px"/>
					<div class="form-group">
					<button type="button" class="btn btn-default" onclick="reset_settings()">Reset</button>
					<button type="button" class="btn btn-primary" onclick="save_app_theme()">Save Changes</button>
					
					</div>
				</form>
				<div class="alert alert-info" role="alert" id="settings_info"
				style="display: none">Saving settings...</div>
			</xf:Tab>		
		
		
		<!-- (+) vijani (start) -->
	
			<xf:Tab name="Account" active="false">
			<h3>Change Password</h3>
				<form style="padding: 20px">
					<xf:TextField id="oldpassword" label="Old Password" placeHolder="Password" type="password" name="old_password" />
					<xf:TextField id="newpassword" label="New Password" placeHolder="Password" type="password" name="new_password" />
					<xf:TextField id="retypenewpassword" label="Retype New Password" placeHolder="Password" type="password" name="retype_new__password" />
					<div class="form-group">
					<button type="button" class="btn btn-default" onclick="reset_settings()">Reset</button>
					<button type="button" class="btn btn-primary" onclick="save_account_settings()">Save Changes</button>
					
					</div>
				</form>
				<div class="alert alert-info" role="alert" id="settings_info"
				style="display: none">Saving settings...</div>
			</xf:Tab>
			</xf:TabBody>
		<!-- (+) vijani (end) -->
		
	<xf:JSImports />
	<script src="./js/settings.js"></script>
</body>
</html>