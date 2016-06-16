<jsp:include page="top.jsp"/>

<% if ( request.getParameter("error") != null ) { %>
     <%-- TODO Escape and encode ${param.error} properly. It can be done using jstl c:out. --%>
     <span style="color: red;">${param.error}</span>
 <% } %>

    <h3> Sign In</h3>
<form action="signin" method="post">
       <fieldset style="width: 300px">
           <legend> Sign is as App User </legend>
           <table>
               <tr>
                   <td>User ID</td>
                   <td><input type="text" name="username" required="required" /></td>
               </tr>
               <tr>
                   <td>Password</td>
                   <td><input type="password" name="userpass" required="required" /></td>
               </tr>
               <tr>
                   <td><input type="submit" value="Sign In App User" /></td>
               </tr>
           </table>
       </fieldset>
   </form>
<jsp:include page="bottom.jsp"/>
