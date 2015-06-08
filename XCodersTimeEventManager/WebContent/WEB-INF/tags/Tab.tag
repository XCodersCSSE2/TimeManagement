<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="name" required="true" %>
<%@attribute name="active" type="java.lang.Boolean" %>
<div role="tabpanel" class="tab-pane <c:if test="${active}">active</c:if>" id="${name}">
<jsp:doBody/>
</div>