function getXmlHttpObject() {
	var xmlHttp = null;
	try {
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (xmlHttp == null) {
		window.alert("Your browser does not support AJAX!");
		return;
	} else {
		return xmlHttp;
	}
}

function ajaxPost(action, params, callback) {
	var queryString = "";
	for(key in params){
		queryString += key + "=" + params[key] + "&" ;
	}
	queryString = queryString.substring(0, queryString.length - 1)
	var request = getXmlHttpObject();
	request.open("POST", action, true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	request.onload = function(){
		callback( request.responseText );
	}
	request.send(queryString);
}

function _(id){
	return document.getElementById(id);
}

function _value(id){
	return document.getElementById(id).value;
}