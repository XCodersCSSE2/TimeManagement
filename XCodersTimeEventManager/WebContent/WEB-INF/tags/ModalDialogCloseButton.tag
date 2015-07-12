<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="onClick" %>
<%@attribute name="text" %>
<button type="button" class="btn btn-default" data-dismiss="modal" onclick="${onClick}">
<c:if test="${empty text }">
Close
</c:if>
${text}
</button>