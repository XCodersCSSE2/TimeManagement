<%-- 
 File info : Class Test jpa model.
 File History
 ----------------------------------------------------
 date		index	    name	    info
 ----------------------------------------------------
 20150715  13208221 Ishantha    map view
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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>My Calendar</title>
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
						data-toggle="dropdown" aria-expanded="false" style="height: 34px">
						Calendar Options <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><xf:ModalDialogToggleText dialogId="newCalendar"
								text="Create New Calendar" /></li>
						<li><xf:ModalDialogToggleText dialogId="deleteCalendars"
								text="Delete Calendar" /></li>
						<li class="divider"></li>
						<li><a href="#">Export Calendar</a></li>
					</ul>
					<form action="about.jsp" style="display: inline;">
						<button type="submit" class="btn btn-default" style="height: 34px">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						</button>
					</form>
					<form action="settings.jsp" style="display: inline;">
						<button type="submit" class="btn btn-default" style="height: 34px">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</button>
					</form>
				</div>
			</div>
			<div class="row" style="padding: 10px; margin-left: 5px">
				<div class="list-group" id="calendarList">
					<c:forEach items="${calendarList}" var="c">
						<a class="list-group-item" id="a_c_${c.id}">
							<div class="row">
								<div class="col-sm-6" id="c_${c.id}" onclick="setDefaultCalendar(${c.id});"
										style="cursor: pointer;">
									<span >${c.name}</span>
								</div>
								<div class="col-sm-6">
									<div class="btn-group btn-group-sm" role="group"
										aria-label="..." style="float: right;">

										<button type="button" class="btn btn-default"
											onclick="edit_calendar_copy_id(${c.id},'${c.name}')"
											data-toggle="modal" data-target="#editCalendar">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										</button>
										<button type="button"
											onclick="share_calendar_copy_id(${c.id},'${c.name}')"
											<c:choose>
										<c:when test="${c.owner.userName eq pageContext.request.remoteUser}" >
											class="btn btn-info"
											data-toggle="modal" data-target="#shareCalendar"
										</c:when>
										<c:otherwise>
											class="btn"
											data-toggle="tooltip" data-placement="top" title="This calendar is shared to you by ${c.owner.userName}"
										</c:otherwise>
									</c:choose>>
											<span class="glyphicon glyphicon-share" aria-hidden="true"></span>
										</button>
										<button type="button" class="btn btn-danger"
											onclick="delete_calendar_copy_id(${c.id},'${c.name}')"
											data-toggle="modal" data-target="#deleteCalendar">
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										</button>
									</div>
								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="col-md-9"
			style="border-left-style: solid; border-left-width: 1px; border-left-color: silver;">
			<x:DHXPlanner theme="${theme_planner}" miniCalendar="true" />
		</div>
	</div>
	<!--Dialog to create calendar  -->
	<xf:ModalDialog dialogId="newCalendar" title="Create New Calendar">
		<xf:ModalDialogBody>
			<form>
				<xf:TextField id="calendarName" label="Calendar Name" type="text"
					placeHolder="Name of the new calendar" />
			</form>
			<div class="alert" role="alert" id="create_calendar_info"
				style="display: none"></div>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter id="create_calendar_footer">
			<xf:ModalDialogCloseButton />
			<button class="btn btn-primary" onclick="create_calendar()">Create</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>

	<!--Dialog to edit calendar  -->
	<xf:ModalDialog dialogId="editCalendar" title="Edit Calendar">
		<xf:ModalDialogBody>
			<form>
				<input type="hidden" id="editCalendarId" />
				<xf:TextField id="editCalendarName" label="Calendar Name"
					type="text" placeHolder="Name of the new calendar" />
			</form>
			<div class="alert" role="alert" id="edit_calendar_info"
				style="display: none"></div>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter id="edit_calendar_footer">
			<xf:ModalDialogCloseButton />
			<button class="btn btn-primary" onclick="edit_calendar()">Save
				Changes</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>

	<!-- Dialog to delete calendar -->
	<xf:ModalDialog dialogId="deleteCalendars" title="Delete Calendars">
		<xf:ModalDialogBody>
			<table class="table table-striped table-bordered table-condensed "
				style="font-size: 14px" id="del_table">
				<c:forEach items="${calendarList}" var="c">
					<tr>
						<td style="width: 10px"><input type="checkbox"></td>
						<td>${c.name}</td>
						<td style="display: none">${c.id}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="alert" role="alert" id="delete_calendars_info"
				style="display: none"></div>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter id="delete_calendars_footer">
			<xf:ModalDialogCloseButton />
			<button class="btn btn-danger" onclick="delete_calendars()">Delete</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>

	<!-- Dialog to confirm delete calendar -->
	<xf:ModalDialog dialogId="deleteCalendar" title="Delete Calendar">
		<xf:ModalDialogBody>
			<p>
				Are you sure you want to delete Calendar <span
					id="deleteCalendarName"></span> ? This will delete all its events
				along with it.
			</p>
			<input type="hidden" id="deleteCalendarId" />
			<div class="alert" role="alert" id="delete_calendar_info"
				style="display: none"></div>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter id="delete_calendar_footer">
			<xf:ModalDialogCloseButton text="Cancel" />
			<button class="btn btn-danger" onclick="delete_calendar()">Delete</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>

	<!-- Dialog to share calendar -->
	<xf:ModalDialog dialogId="shareCalendar" title="Share Calendar">
		<xf:ModalDialogBody>
			<p>
				Share <span id="shareCalendarName"></span> with :
			<p>
			<form>
				<xf:TextField id="member_select" label="" />
				<div class="form-group">
					<label for=""></label>
					<button type="button" class="btn btn-default"
						onclick="share_add_member()">Add Member</button>
				</div>
			</form>
			<div style="overflow: scroll; height: 200px;">
				<table
					class="table table-striped table-bordered table-hover table-condensed"
					id="share_table">
					<tr>
						<th></th>
						<th style="width: 10px"></th>
					</tr>

				</table>
			</div>
			<input type="hidden" id="shareCalendarId" />
			<br />
			<div class="alert" role="alert" id="share_calendar_info"
				style="display: none"></div>
		</xf:ModalDialogBody>
		<xf:ModalDialogFooter id="share_calendar_footer">
			<xf:ModalDialogCloseButton />
			<button class="btn btn-primary" onclick="share_calendar()">Share</button>
		</xf:ModalDialogFooter>
	</xf:ModalDialog>

	<xf:JSImports />
	<script src="./js/calendar.js"></script>

	<%-- (+) 20150715 Ishantha (Start) --%>
	<script src="./js/map.js"></script>
	<%-- (+) 20150715 Ishantha (End) --%>

</body>
</html>