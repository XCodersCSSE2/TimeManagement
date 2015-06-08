<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="text" %>
<%@attribute name="dialogId"  required="true" rtexprvalue="true"%>

<a data-toggle="modal" data-target="#${dialogId}" style="cursor: pointer;" >${text}</a>