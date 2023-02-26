<%-- 
    Document   : login
    Created on : Feb 12, 2023, 2:45:42 PM
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="./css/login.css">
        
        <% 
    if(request.getAttribute("errorMessage")!=null){
        out.println("<style>.status{visibility:visible;"
+"    width: 100%;"
+"    height:40px; "
+"    display: inline-block;"
+"           }</style>");
    }
        %>
      <title>Personal time management - Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="row">
            <div class="column side">
            <button class="backbutton" onclick="location.href ='./'">Go Back</button></div>
            <div class="column middle">
                <form class="form" name="loginForm" action="login" method="POST">
                    <div class="fhead">Login</div>
                    <label for="user">Username:</label>
                    <input type="text" name="username" id="username" placeholder="Username" autofocus required />
                    <label for="pwd">Password:</label>
                    <input type="password" name="password" id="pwd" placeholder="Password" required/>
                    <div id="status" class="loginStatus"><p class="msg">
                        <%if(request.getAttribute("errorMessage")!=null)
                        {
                            out.println(request.getAttribute("errorMessage"));
                        }
                        %></p>
                    </div>
                    <div>
                        <input type="checkbox" name="rememberme" id="rm" value="ON" style="display:inline;width: 10%;"/><label for="rm" name="rememberme">Remember me?</label>
                    </div>
                    <input type="submit" value="Login" name="login" />
                    <div class="bottom-text"><a href="create">Create account?</a> Or <a href="forgot.jsp">forgot password.</a></div>
                </form>
            </div>
            <div class="column side"></div>
        </div>
    </body>
</html>
