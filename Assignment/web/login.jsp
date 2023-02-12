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
+"    text-align: justify;"
+"    text-indent: 5%;"
+"    vertical-align:center;"
+"    margin-top: 10px;"
+"    margin-bottom: 10px;"
+"    display: inline-block;"
+"    background: #ff8080;"
+"    color: yellow;"
+"    display: inline-block;}</style>");
    }
        %>
      <title>Personal time management - Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <button class="backbutton" onclick="index.html">Go Back</button>
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
                        <%
    if(request.getAttribute("errorMessage")!=null)
    {
        out.println(request.getAttribute("errorMessage"));
    }
    %></p></div>
                    <input type="submit" value="Login" name="login" />
                </form>
            </div>
            <div class="column side"></div>
        </div>
    </body>
</html>