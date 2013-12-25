<%
    session=request.getSession(false);
    if(session.getAttribute("user")!=null)
    {
        response.sendRedirect("./welcome.jsp");
    }

%>


<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">   

<html xmlns = "http://www.w3.org/1999/xhtml">

<head>
   <title>Login</title>

   <style type = "text/css">
      body { 
         font-family: tahoma, helvetica, arial, sans-serif;
      }

      table, tr, td { 
         font-size: .9em;
         border: 3px groove;
         padding: 5px;
         background-color: #dddddd;
      }
      
      .errorMessage{
      	color: red;
      }
   </style>
</head>

<body>

   <% // start scriptlet

      if ( request.getAttribute("errorMessage") == null ) {
    	  
   %> <%-- end scriptlet to insert fixed template data --%>
        <form method = "post" action = "./Login">
            <p>Enter your login and your password.</p>
            <table>
               <tr>
                  <td>Login</td>

                  <td>
                     <input type = "text" name = "login" />
                  </td>
               </tr>

               <tr>
                  <td>Password</td>

                  <td>
                     <input type = "password" name = "pwd" />
                  </td>
               </tr>
               <tr>
                  <td colspan = "2">
                     <input type = "submit" 
                        value = "Submit" />
                  </td>
               </tr>
            </table>
        </form>
         
         

   <% // continue scriptlet

      }  // end if
      else {
        %>
        	 <form method = "post" action = "./Login">
	            <p>Enter your login and your password.</p>
		            <table>
		               <tr>
		                  <td>Login</td>
		
		                  <td>
		                     <input type = "text" name = "login" />
		                  </td>
		               </tr>
		
		               <tr>
		                  <td>Password</td>
		
		                  <td>
		                     <input type = "password" name = "pwd" />
		                  </td>
		               </tr>
		               <tr>
		                  <td colspan = "2">
		                     <input type = "submit" 
		                        value = "Submit" />
		                  </td>
		               </tr>
		            </table>
        	</form>
        	 <p class = "errorMessage"><%out.println(request.getAttribute("errorMessage"));%></p><%
         } %>

		Not registered ? <a href="./userRegister.jsp" target="_blank">Create an account.</a>
</body>

</html>