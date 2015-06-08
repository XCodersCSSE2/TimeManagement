<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<%@attribute name="dialogId" required="true" rtexprvalue="true"%>
<%@attribute name="title" %>

<!-- Modal -->
<div class="modal fade" id="${dialogId}" tabindex="-1" role="dialog" aria-labelledby="${dialogId}_Label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="${dialogId}_Label">${title}</h4>
      </div>
      <jsp:doBody />
    </div>
  </div>
</div>
