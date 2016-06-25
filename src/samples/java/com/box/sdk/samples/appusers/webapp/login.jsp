<jsp:include page="top.jsp"/>

    <% if ( request.getAttribute("error") != null ) { %>
        <%-- TODO Escape and encode ${param.error} properly. It can be done using jstl c:out. --%>
        <span style="color: red;">${error}</span>
    <% } %>
    <h3><i class="fa fa-exclamation-circle"></i> ${connectMessage} </h3>

    <div class="row signin-button">
        <div class="col-md-3"></div>
        <div class="col-md-3">
            <a href="/signin.jsp" class="btn btn-primary btn-lg btn-login btn-block" >Sign In App User</a>
        </div>
        <div class="col-md-3">
            <a href="/createuser.jsp" class="btn btn-primary btn-lg btn-login btn-block" >Create App User</a>
        </div>
        <div class="col-md-4"></div>
    </div>
<jsp:include page="bottom.jsp"/>
