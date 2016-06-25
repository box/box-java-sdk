<jsp:include page="top.jsp"/>

    <% if ( request.getParameter("error") != null ) { %>
        <%-- TODO Escape and encode ${param.error} properly. It can be done using jstl c:out. --%>
        <span style="color: red;">${param.error}</span>
    <% } %>

    <h2>Your Dashboard</h2>
    <h3><i class="fa fa-exclamation-circle"></i> ${dashboardMessage} </h3>
    <hr/>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <h3>Folders</h3>
    <c:forEach items="${folders}" var="folder">
        <h4>${folder.getName()}</h4>
    </c:forEach>

    <h3>Files</h3>
    <c:forEach items="${files}" var="file">
        <div class="row" style="margin-left: 0px;">
            <a href="/doc?id=${file.getID()}"><c:out value="${file.getName()}"/></a>
        </div>
    </c:forEach>

    <br/>
    <h4>Upload File using CORS</h4>
    <div class="row">
        <div class="col-md-4">
            <form action='blah' id='file-form' method='POST'>
                <div class='form-group'>
                    <input class='form-control' id='file-select' name='files' type='file'>
                </div>
                <div class='form-group'>
                    <button class='btn btn-default' id='upload-button' type='submit'>
                        Upload
                    </button>
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript">
      var form = document.getElementById('file-form');
      var fileSelect = document.getElementById('file-select');
      var uploadButton = document.getElementById('upload-button');

      form.onsubmit = function(event){
        event.preventDefault();
        uploadButton.innerHTML = 'Uploading...';

        // The Box Auth Header. Add your access token.
        var headers = { Authorization: 'Bearer ${accessToken}'};
        var uploadUrl = 'https://upload.box.com/api/2.0/files/content';

        var files = fileSelect.files;
        var formData = new FormData();

        formData.append('files', files[0], files[0].name);

        // Add the destination folder for the upload to the form
        formData.append('parent_id', '0');

        $.ajax({
            url: uploadUrl,
            headers: headers,
            type: 'POST',
            // This prevents JQuery from trying to append the form as a querystring
            processData: false,
            contentType: false,
            data: formData
        }).complete(function ( data ) {
            uploadButton.innerHTML = 'Upload';
            // Log the JSON response to prove this worked
            console.log(data.responseText);
            location.reload(true);
        });
       }
      </script>



<jsp:include page="bottom.jsp"/>
