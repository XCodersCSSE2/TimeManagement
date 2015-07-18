function save_app_theme() {
	_("settings_info").style.display = "";

	var params = {
		name : "theme_app",
		value : _("app_theme_text").innerHTML
	};
	ajaxPost("SaveSetting", params, save_planner_theme);

}

function save_planner_theme(response,params) {
	if (response === "s") {
		var params = {
				name : "theme_planner",
				value : _("planner_theme_text").innerHTML
			};
			ajaxPost("SaveSetting", params, save_themes_callback);
	} else {
		_("settings_info").innerHTML = response;
	}

	

}

function save_themes_callback(response,params) {
	if (response === "s") {
		_("settings_info").style.display = "none";
		//location.reload();
	} else {
		_("settings_info").innerHTML(response);
	}
}

function reset_settings(){
	location.reload();
}