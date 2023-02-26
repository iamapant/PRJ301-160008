<%-- 
    Document   : confirm-info
    Created on : 26 Feb 2023, 11:51:56 am
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="model.Settings" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/cinfo.css">
    </head>
    <body>
        <% User u = (User)request.getAttribute("createUser");
        //TODO CHECK NULL
        
        
        
        
        
//            try{
//                if(u.getName() == null){
//                    response.sendRedirect("login.jsp");
//                }
//            }
//            catch(Exception ex){
//                response.sendRedirect("login.jsp");
//            }
        %>
        <div class="row">
            <div class="column side">
                <!--<button class="backbutton" onclick="location.href ='./'">Go Back</button>-->
            </div>
            <div class="column middle">
                <form class="form" name="loginForm" action="login" method="POST">
                    <div class="fhead">Confirm User Information</div>
                    <div>
                        <label for="user">Username:</label>
                        <p><%= u.getName() %></p>
                    </div>
                </form>
            </div>
            <div class="column side"></div>
        </div>
    </body>
</html>
