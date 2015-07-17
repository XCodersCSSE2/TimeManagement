//create calendar functions
function create_calendar() {
	create_calendar_info_show("Please Wait...", "info", true);

	var params = {
		name : _("calendarName").value
	};
	ajaxPost("CreateCalendar", params, create_calendar_callback);

}

function create_calendar_callback(response,params) {
	if (response === "s") {
		$('#newCalendar').modal('hide');
		_("calendarList").innerHTML += "<a class=\"list-group-item\">"
				+ _("calendarName").value + "</a>";
		create_calendar_info_hide();
	} else {
		create_calendar_info_show(response, "warning", false);
	}
}

function create_calendar_info_show(message, type, hide_footer) {
	_("create_calendar_info").setAttribute("class", "alert alert-" + type);
	_("create_calendar_info").innerHTML = message;
	_("create_calendar_info").style.display = "";
	if (hide_footer) {
		_("create_calendar_footer").style.display = "none";
	} else {
		_("create_calendar_footer").style.display = "";
	}
}

function create_calendar_info_hide() {
	_("create_calendar_info").style.display = "none";
	_("create_calendar_footer").style.display = "";
	_("calendarName").value = "";

}

//edit calendar functions
function edit_calendar_copy_id(id,name){
	_("editCalendarId").value = id;
	_("editCalendarName").value = name;
}

function edit_calendar() {
	edit_calendar_info_show("Please Wait...", "info", true);

	var params = {
		id : _("editCalendarId").value,
		name : _("editCalendarName").value
	};
	ajaxPost("EditCalendar", params, edit_calendar_callback);

}

function edit_calendar_callback(response,params) {
	if (response === "s") {
		$('#editCalendar').modal('hide');
		_("c_"+params.id).innerHTML =  _("editCalendarName").value;
		edit_calendar_info_hide();
	} else {
		create_calendar_info_show(response, "warning", false);
	}
}

function edit_calendar_info_show(message, type, hide_footer) {
	_("edit_calendar_info").setAttribute("class", "alert alert-" + type);
	_("edit_calendar_info").innerHTML = message;
	_("edit_calendar_info").style.display = "";
	if (hide_footer) {
		_("edit_calendar_footer").style.display = "none";
	} else {
		_("edit_calendar_footer").style.display = "";
	}
}

function edit_calendar_info_hide() {
	_("edit_calendar_info").style.display = "none";
	_("edit_calendar_footer").style.display = "";
	_("editCalendarId").value = "";
	_("editCalendarName").value = "";

}

//delete calendar functions
function delete_calendar_copy_id(id,name){
	_("deleteCalendarId").value = id;
	_("deleteCalendarName").innerHTML = name;
}

function delete_calendar() {
	delete_calendar_info_show("Please Wait...", "info", true);

	var params = {
		id : _("deleteCalendarId").value,
	};
	ajaxPost("DeleteCalendar", params, delete_calendar_callback);
}

function delete_calendar_callback(response,params) {
	if (response === "s") {
		$('#deleteCalendar').modal('hide');
		_("a_c_"+params.id).style.display = "none";
		delete_calendar_info_hide();
	} else {
		delete_calendar_info_show(response, "warning", false);
	}
}

function delete_calendar_info_show(message, type, hide_footer) {
	_("delete_calendar_info").setAttribute("class", "alert alert-" + type);
	_("delete_calendar_info").innerHTML = message;
	_("delete_calendar_info").style.display = "";
	if (hide_footer) {
		_("delete_calendar_footer").style.display = "none";
	} else {
		_("delete_calendar_footer").style.display = "";
	}
}

function delete_calendar_info_hide() {
	_("delete_calendar_info").style.display = "none";
	_("delete_calendar_footer").style.display = "";
	_("deleteCalendarId").value = "";	
}

function setDefaultCalendar(id){
	var params = {
			name : "default-calendar",
			value : id
		};
		ajaxPost("SaveSetting", params, setDefaultCalendar_callback);
}

function setDefaultCalendar_callback(id){
	location.reload();
}
