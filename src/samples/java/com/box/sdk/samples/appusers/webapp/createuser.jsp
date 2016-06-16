<jsp:include page="top.jsp"/>

<% if ( request.getParameter("error") != null ) { %>
     <%-- TODO Escape and encode ${param.error} properly. It can be done using jstl c:out. --%>
     <span style="color: red;">${param.error}</span>
 <% } %>

    <h3> Create New App User</h3>
<form action="createuser" method="post">
       <fieldset style="width: 300px">
           <legend> Create App User </legend>
           <table>
               <tr>
                   <td>Create App User</td>
                   <td><input type="text" name="username" required="required" /></td>
               </tr>
               <tr>
                   <td>Password</td>
                   <td><input type="password" name="userpass" required="required" /></td>
               </tr>
             <tr>
                  <td>Retype Password</td>
                  <td><input type="password" name="verifyuserpass" required="required" /></td>
              </tr>
               <tr>
                   <td><input type="submit" value="Create App User" /></td>
               </tr>
           </table>
       </fieldset>
   </form>
<jsp:include page="bottom.jsp"/>
