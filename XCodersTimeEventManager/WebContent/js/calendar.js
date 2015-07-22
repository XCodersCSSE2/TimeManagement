//create calendar functions
function create_calendar() {
	create_calendar_info_show("Please Wait...", "info", true);

	var params = {
		name : _("calendarName").value
	};
	ajaxPost("CreateCalendar", params, create_calendar_callback);

}

function create_calendar_callback(response, params) {
	if (response === "s") {
		$('#newCalendar').modal('hide');
		_("calendarList").innerHTML += "<a class=\"list-group-item\">"
				+ _("calendarName").value + "</a>";	
		create_calendar_info_hide();
		location.reload();
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

// edit calendar functions
function edit_calendar_copy_id(id, name) {
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

function edit_calendar_callback(response, params) {
	if (response === "s") {
		$('#editCalendar').modal('hide');
		_("c_" + params.id).innerHTML = _("editCalendarName").value;
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

// delete calendar functions
function delete_calendar_copy_id(id, name) {
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

function delete_calendar_callback(response, params) {
	if (response === "s") {
		$('#deleteCalendar').modal('hide');
		_("a_c_" + params.id).style.display = "none";
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

//delete multiple calenders at once
function delete_calendars(){
	var table = _("del_table");
	var idList = "";
	for (i = 0; i < table.rows.length; i++) {
		if(table.rows[i].cells[0].getElementsByTagName("input")[0].checked){
			idList += table.rows[i].cells[2].innerHTML + ";";
		}		
	}
	idList = idList.substring(0, idList.length - 1);
	delete_calendars_info_show("Please Wait...", "info", true);

	var params = {
		ids : idList
	};
	ajaxPost("DeleteCalendars", params, delete_calendars_callback);
}

function delete_calendars_callback(response, params) {
	if (response === "s") {
		$('#deleteCalendars').modal('hide');
		delete_calendars_info_hide();
		var table = _("del_table");
		while(table.rows.length > 1) {
			  table.deleteRow(1);
		}
		var ids = params.ids.split(";");
		for(i = 0 ; i < ids.length;i++){
			_("a_c_" + ids[i]).style.display = "none";
		}
		delete_calendar_info_hide();
	} else {
		delete_calendars_info_show(response, "warning", false);
	}
}

function delete_calendars_info_show(message, type, hide_footer) {
	_("delete_calendars_info").setAttribute("class", "alert alert-" + type);
	_("delete_calendars_info").innerHTML = message;
	_("delete_calendars_info").style.display = "";
	if (hide_footer) {
		_("delete_calendars_footer").style.display = "none";
	} else {
		_("delete_calendars_footer").style.display = "";
	}
}

function delete_calendars_info_hide() {
	_("delete_calendars_info").style.display = "none";
	_("delete_calendars_footer").style.display = "";
}

// share calendar functions
function share_calendar_copy_id(id, name) {
	_("shareCalendarId").value = id;
	_("shareCalendarName").innerHTML = name;
	_("member_select").value = "";
	clear_shared_table();
	share_calendar_info_show("Loading share details...", "info", true);
	var params = {
		id : id
	};
	ajaxPost("GetSharedMembers", params, share_calander_data_callback);
}

function share_calander_data_callback(response, params) {
	if (response !== "Error") {
		if (response.length > 0) {
			var names = response.split(";");
			for (i = 0; i < names.length; i++) {
				add_to_shared_table(names[i]);
			}
		}
		share_calendar_info_hide();
	} else {
		share_calendar_info_show(response, "warning", false);
	}
}

function share_calendar() {
	share_calendar_info_show("Please Wait...", "info", true);
	var calendarIds = "";
	var table = _("share_table");

	for (i = 1; i < table.rows.length; i++) {
		calendarIds += table.rows[i].cells[0].innerHTML + ";";

	}

	calendarIds = calendarIds.substring(0, calendarIds.length - 1);
	var params = {
		id : _("shareCalendarId").value,
		list : calendarIds
	};
	ajaxPost("ShareCalendar", params, share_calendar_callback);

}

function share_calendar_callback(response, params) {
	if (response === "s") {
		$('#shareCalendar').modal('hide');

		share_calendar_info_hide();
	} else {
		share_calendar_info_show(response, "warning", false);
	}
}

function share_calendar_info_show(message, type, hide_footer) {
	_("share_calendar_info").setAttribute("class", "alert alert-" + type);
	_("share_calendar_info").innerHTML = message;
	_("share_calendar_info").style.display = "";
	if (hide_footer) {
		_("share_calendar_footer").style.display = "none";
	} else {
		_("share_calendar_footer").style.display = "";
	}
}

function share_calendar_info_hide() {
	_("share_calendar_info").style.display = "none";
	_("share_calendar_footer").style.display = "";

}

function check_duplicate_share_member(name) {
	var table = _("share_table");

	for (i = 0; i < table.rows.length; i++) {
		if (table.rows[i].cells[0].innerHTML === name) {
			return true;
		}
	}
	return false;
}

function share_add_member() {
	var memberUserName = _("member_select").value;

	if (memberUserName.length < 1) {
		share_calendar_info_show("Please insert a member username to share",
				"warning", false);
		return;
	}
	if (check_duplicate_share_member(memberUserName)) {
		share_calendar_info_show("Member already added", "warning", false);
		return;
	}

	var params = {
		name : memberUserName
	};
	ajaxPost("UserNameExists", params, share_add_member_validated);

}

function share_add_member_validated(response, params) {
	if (response === "n") {
		share_calendar_info_show("Member does not exist", "warning", false);
		return;
	}

	add_to_shared_table(params.name);
	_("member_select").value = "";
	share_calendar_info_hide()
}

function add_to_shared_table(name) {
	var table = _("share_table");
	var row = table.insertRow(-1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);

	cell1.innerHTML = name;
	cell2.innerHTML = "<button class=\"btn btn-danger btn-xs\" type=\"button\" onclick=\"remove_from_shared_table('"+name+"')\"><span class=\"glyphicon glyphicon-trash\"></span></button>";
}

function remove_from_shared_table(name) {
	var table = _("share_table");

	for (i = 0; i < table.rows.length; i++) {
		if (table.rows[i].cells[0].innerHTML === name) {
			table.deleteRow(i);
			break;
		}
	}
}

function clear_shared_table() {
	var table = _("share_table");
	while(table.rows.length > 1) {
		  table.deleteRow(1);
	}
}

// change calendar on click
function setDefaultCalendar(id) {
	var params = {
		name : "default-calendar",
		value : id
	};
	ajaxPost("SaveSetting", params, setDefaultCalendar_callback);
}

function setDefaultCalendar_callback(response, params) {
	location.reload();
}
