<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="name" required="true" %>
<%@attribute name="active" type="java.lang.Boolean" %>
 <li role="presentation"
 <c:if test="${active}">
 class="active"
 </c:if>
 >
 <a href="#${name}" aria-controls="${name}" role="tab" data-toggle="tab">${name}</a>
 </li>
   