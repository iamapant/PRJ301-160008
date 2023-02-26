<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.User"  %>
<%@page import="jakarta.servlet.http.HttpServletResponse" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Main page</title>
        <link rel="stylesheet" href="./css/main.css">
        <% 
            //TODO CHECK NULL
            
            
            
            User u = new User();
            u =(User)session.getAttribute("user"); 
            if(u == null || u.isEmpty()){
                response.sendRedirect("login.jsp");
            } 
        %>
    </head>
    <body>
        <div class="ribbon">
            <div class="setting">
                <input type="submit" value="☰" class="settingBtn" id="settingBtn" onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = 'transparent'" onclick="showRight()"/>
                <img src="icon.jpg" class="icon" onclick="location.href ='setting.jsp'" onmouseover="this.style.border = '6px solid white'" onmouseout="this.style.border = '6px solid transparent'">
            </div>
        </div>
        <div class="left" id="left">
            <div class="upcoming">
                <h2>Upcoming</h2>
                <%= u.getName() %> 
            </div>
            <div class="calendar">
                <h2>Calendar</h2>
                <%= u.getEmail() %>
            </div>
        </div>
        <div class="right" id="right">
            <div class="suggestion">
                <h2>Suggestion</h2>
                <%= u.getSetting().getA_Time1() %> 
            </div>
        </div>
            <script>
                function showRight(){
                    var x = document.getElementById("right");
                    if(x.style.display === "none"){
                        x.style.display = "block";
                        document.getElementById("left").style.width = "80%";
                    }
                    else {
                        x.style.display = "none";
                        document.getElementById("left").style.width = "100%";
                    }
                }
            </script>
    </body>
</html>
