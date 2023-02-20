<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.User"  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main page</title>
        <link rel="stylesheet" href="main.css">
        <% 
            User u = (User)request.getAttribute("currentUser"); 
            if(u == null || u.getName() == null || u.getPass() == null){
                response.sendRedirect("index.html");
            }
        %>
    </head>
    <body>
        <div class="ribbon">
            <div class="setting">
                <input type="submit" value="☰" class="settingBtn" id="settingBtn" onclick="chgBG()"/></div>
        </div>
        <div class="left">
            <div class="upcoming">
                <%= u.getName() %>
            </div>
            <div class="calendar">
                <%= u.getEmail() %>
            </div>
        </div>
        <div class="right">
            <div class="suggestion">
                <%= u.getSetting().getA_Time1() %>
            </div>
        </div>
    </body>
    <script>
        function chgBG(){
                document.getElementsById("settingBtn").style["background"] = "red";
        }
    </script>
</html>
