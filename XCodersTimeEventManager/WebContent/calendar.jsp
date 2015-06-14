<%-- 
 File info : Class Test jpa model.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150604  13208316	ravindu		created.
 ----------------------------------------------------
 --%>
<%@page import="java.util.Date"%>
<%@page import="com.dhtmlx.planner.controls.DHXMapView"%>
<%@page import="com.dhtmlx.planner.controls.DHXMiniCalendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="x" uri="/WEB-INF/tlds/eventtime"%>
<%@taglib prefix="xf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<xf:CSSImports />
</head>
<body>
	<xf:NavBar />
	<xf:PageHeader subtext="plan your events using the calendar"
		text="My Calendar" />
	<div class="row">
		<div class="col-md-3">
			<div class="row" style="padding: 5px; margin-left: 5px;">
				<x:DHXMiniCalendar />
			</div>
			<div class="row" style="text-align: center; padding-top: 5px;">
				<div class="btn-group" role="group" aria-label="...">

					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						Calendar Options <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li>
							<xf:ModalDialogToggleText dialogId="newCalendar" text="Create New Calendar"/>
						</li>
						<li><a href="#">Delete Calendar</a></li>
						<li class="divider"></li>
						<li><a href="#">Export Calendar</a></li>
					</ul>
					<button type="button" class="btn btn-default">Middle</button>
					<button type="button" class="btn btn-default">Right</button>
				</div>
			</div>
			<div class="row" style="padding: 10px;margin-left: 5px">
				<div class="list-group" id="calendarList">
					<a class="list-group-item">My Calendar</a>
					<a class="list-group-item">Work</a>
					<a class="list-group-item">Meetings</a>
					<a class="list-group-item">New Calendar1</a>
					<a class="list-group-item">New Calendar2</a>
				</div>
			</div>
		</div>

		<div class="col-md-9"
			style="border-left-style: solid; border-left-width: 1px; border-left-color: silver;">
			<x:DHXPlanner theme="terrace" miniCalendar="true" />
		</div>
	</div>
	<xf:ModalDialog dialogId="newCalendar" title="Create New Calendar">
		<xf:ModalDialogBody>
			<form >
				<xf:TextField id="calendarName" label="Calendar Name" type="text" placeHolder="Name of the new calendar"/>
			</form>
			<div class="alert" role="alert" id="create_calendar_info" style="display: none" ></div>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter id="create_calendar_footer">
			<xf:ModalDialogCloseButton/>
			<button class="btn btn-primary" onclick="create_calendar()">Create</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>
	<xf:JSImports />
	<script>
		function create_calendar(){
			create_calendar_info_show("Please Wait...", "info", true);
			
			var params = {
				name : _("calendarName").value
			};
			ajaxPost("CreateCalendar", params, create_calendar_callback);

		}

		function create_calendar_callback(response) {		
			if (response === "s") {
				$('#newCalendar').modal('hide');
				_("calendarList").innerHTML += "<a class=\"list-group-item\">" + _("calendarName").value + "</a>" ;
				signup_info_hide();
			} else {
				create_calendar_info_show(response, "warning", false);
			}
		}
		
		function create_calendar_info_show(message,type,hide_footer){
			_("create_calendar_info").setAttribute("class", "alert alert-" + type);
			_("create_calendar_info").innerHTML = message;
			_("create_calendar_info").style.display = "";
			if(hide_footer){
				_("create_calendar_footer").style.display = "none";
			}else{
				_("create_calendar_footer").style.display = "";
			}
		}
		
		function create_calendar_info_hide(){
			_("create_calendar_info").style.display = "none";
			_("create_calendar_footer").style.display = "";
			_("calendarName").value = "";

		}
	</script>
</body>
</html>