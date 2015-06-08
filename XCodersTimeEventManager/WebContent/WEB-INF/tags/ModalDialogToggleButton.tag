<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="text" %>
<%@attribute name="dialogId"  required="true" rtexprvalue="true"%>
<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#${dialogId}">${text}</button>