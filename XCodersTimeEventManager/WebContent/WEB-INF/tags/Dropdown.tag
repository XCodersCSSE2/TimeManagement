<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="label"%>
<%@attribute name="id" required="true"%>
<%@attribute name="list" type="java.util.List"%>
<%@attribute name="value"%>
<%@attribute name="name"%>
<%@attribute name="width"%>
<div class="form-group">
	<label for="${id}">${label}</label>
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="${id}" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true"
			style="width:${width};text-align:right"
			>
			<span id="${id}_text" >${value}</span>&nbsp;&nbsp;&nbsp; <span class="caret" ></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="${id}">
			<c:forEach items="${list}" var="i">
				<li><a onclick="_('${id}_text').innerHTML='${i}'">${i}</a></li>
			</c:forEach>
		</ul>
	</div>
	<%--<input type="${type}" class="form-control" id="${id}"
		placeholder="${placeHolder}" name="${name}"> --%>
</div>
