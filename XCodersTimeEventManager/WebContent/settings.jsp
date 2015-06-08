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
				Theme Settings 
			</xf:Tab>
			<xf:Tab name="Account">
				Account Settings 
			</xf:Tab>
		</xf:TabBody>
	<xf:JSImports />
</body>
</html>