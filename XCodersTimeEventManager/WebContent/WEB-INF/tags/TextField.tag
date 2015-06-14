<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="label" %>
<%@attribute name="id" required="true" %>
<%@attribute name="type" %>
<%@attribute name="placeHolder" %>
<%@attribute name="name" %>
<div class="form-group">
	<label for="${id}">${label}</label> 
	<input type="${type}" class="form-control" id="${id}"
		placeholder="${placeHolder}" name="${name}">
</div>
