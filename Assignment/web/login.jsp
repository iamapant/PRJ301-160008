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
        <link rel="stylesheet" href="login.css">
        
        <% 
    if(request.getAttribute("errorMessage")!=null){
        out.println("<style>.loginStatus{visibility:visible;"
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
        <button class="backbutton" onclick="location.href ='index.html'">Go Back</button>
        <div class="row">
            <div class="column side"></div>
            <div class="column middle">
                <form class="form" name="loginForm" action="loginCheck" method="POST">
                    <div class="fhead">Login</div>
                    <label for="user">Username:</label>
                    <input type="text" name="user" id="user" placeholder="Username" autofocus required />
                    <label for="pwd">Password:</label>
                    <input type="password" name="pass" id="pwd" placeholder="Password" required/>
                    <div id="loginStatus" class="loginStatus"><p class="msg">
                        <%if(request.getAttribute("errorMessage")!=null)
                        {
                            out.println(request.getAttribute("errorMessage"));
                        }
                        %></p></div>
                    <input type="submit" value="Login" name="login" />
                    <div class="bottom-text"><a href="create">Create account?</a> Or <a href="forgot">forgot password.</a></div>
                </form>
            </div>
            <div class="column side"></div>
        </div>
    </body>
</html>
