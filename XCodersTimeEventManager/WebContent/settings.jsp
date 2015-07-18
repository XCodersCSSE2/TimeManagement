<%-- 
 File info : settings page.
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
<!DOCTYPE >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<xf:CSSImports />
</head>
<body>
	<xf:NavBar />
	<xf:PageHeader subtext="use this page to edit your settings" text="Settings" />
	<xf:TabPanel>
		<xf:TabList>
			<xf:TabName name="Theme" active="true"/>	
			<xf:TabName name="Account"></xf:TabName>
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
			<xf:Tab name="Account">
				Account Settings 
			</xf:Tab>
		</xf:TabBody>
	<xf:JSImports />
	<script src="./js/settings.js"></script>
</body>
</html>