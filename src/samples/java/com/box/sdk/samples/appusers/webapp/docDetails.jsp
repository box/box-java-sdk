<jsp:include page="top.jsp"/>

    <% if ( request.getParameter("error") != null ) { %>
        <%-- TODO Escape and encode ${param.error} properly. It can be done using jstl c:out. --%>
        <span style="color: red;">${param.error}</span>
    <% } %>

    <div aria-hidden='true' aria-labelledby='myModalLabel' class='modal fade' id='viewModal' role='dialog' tabindex='-1'>
        <div class='modal-dialog'>
          <div class='modal-body' style='overflow: hidden;'>
            <iframe allowfullscreen='allowfullscreen' frameBorder='0' height='100%' id='preview' width='100%'></iframe>
          </div>
          <div class='modal-footer'>
            <button class='btn btn-default btn-small' data-dismiss='modal' type='button' onclick="$('#preview').attr('src','about:blank');">
              Close
            </button>
          </div>
        </div>
    </div>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class='row'>
      <div class='col-md-2'>
        <img src='/thumbnail?id=${fileId}' width='100px'>
      </div>
      <div class='col-md-10' style="margin-left: -80px;">
        <div class='row'>
          <div class='col-md-2'>
            <div class='key'>
              File Name:
            </div>
          </div>
          <div class='col-md-10'>
            <div class='value'>
              ${fileName}
            </div>
          </div>
        </div>
        <div class='row'>
          <div class='col-md-2'>
            <div class='key'>
              Created At:
            </div>
          </div>
          <div class='col-md-10'>
            <div class='value'>
              ${createdAt}
            </div>
          </div>
        </div>
        <div class='row'>
          <div class='col-md-2'>
            <div class='key'>
              Size:
            </div>
          </div>
          <div class='col-md-10'>
            <div class='value'>
              ${fileSizeKB} KB
            </div>
          </div>
        </div>
        <div class='row'>
          <div class='col-md-2'></div>
          <div class='col-md-10'>
            <a href='/download?id=${fileId}'>
              Download
            </a>
          </div>
        </div>
        <c:if test="${supportsPreview}">
            <div class='row'>
              <div class='col-md-2'></div>
              <div class='col-md-10'>
                <a href='#' onclick="$('#preview').attr('src','about:blank'); setTimeout(function(){$('#preview').attr('src','/preview?id=${fileId}'); $('#viewModal').modal(open);},10);">
                  Preview
                </a>
              </div>
            </div>
        </c:if>
      </div>
    </div>



<jsp:include page="bottom.jsp"/>
