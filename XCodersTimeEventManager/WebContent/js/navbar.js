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

function signup_callback(response,params) {

	if (response === "s") {
		$('#dialogSignup').modal('hide');
		signup_info_hide();
		location.reload();
	} else {
		signup_info_show(response, "warning", false);
	}
}

function signup_info_show(message, type, hide_footer) {
	_("signup_info").setAttribute("class", "alert alert-" + type);
	_("signup_info").innerHTML = message;
	_("signup_info").style.display = "";
	if (hide_footer) {
		_("signup_footer").style.display = "none";
	} else {
		_("signup_footer").style.display = "";
	}
}

function signup_info_hide() {
	_("signup_info").style.display = "none";
	_("signup_footer").style.display = "";
	_("name").value = "";
	_("email").value = "";
	_("userName").value = "";
	_("password").value = "";
	_("retypePassword").value = "";
}

var login_callback_called = false;
function signin_submit() {
	var params = {
		password : _("jpassword").value
	};
	ajaxPost("Encrypt", params, signin_submit_callback);
}

function signin_submit_callback(response,params) {
	_("jpassword").value = response;
	login_callback_called = true;
	_("signin_form").submit();
}