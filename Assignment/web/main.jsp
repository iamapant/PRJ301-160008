<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.User"  %>
<%@page import = "DB.UserDAO"  %>
<%@page import = "model.Notice"  %>
<%@page import = "model.SugNotice"  %>
<%@page import = "DB.NoticesDAO"  %>
<%@page import = "DB.SugNoticeDAO"  %>
<%@page import = "java.util.ArrayList"  %>
    <%@page import="jakarta.servlet.http.HttpServletResponse" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Main page</title>
        <link rel="stylesheet" href="./css/main.css">
        <% 
            //TODO CHECK NULL
            
            UserDAO ud = new UserDAO();
            NoticesDAO nd = new NoticesDAO();
            SugNoticeDAO sd = new SugNoticeDAO();
            
            ArrayList<User> ul = ud.getUsers();
            ArrayList<SugNotice> sl = sd.getSugNotices();
            
            
            User u = new User();
            u =(User)session.getAttribute("user"); 
            
            nd.updateRepeatableNotices(u.getId());
        %>
    </head>
    <body>
        <div class="ribbon">
            <div class="setting">
                <input type="submit" value="☰" class="settingBtn" id="settingBtn" onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = 'transparent'" onclick="showRight()"/>
                <img src="pics/icon.jpg" class="icon" onclick="location.href ='setting'" onmouseover="this.style.border = '6px solid white'" onmouseout="this.style.border = '6px solid transparent'">
            </div>
        </div>
        <div class="left" id="left">
            <div class="upcoming">
                <h2>Upcoming</h2>
                <jsp:include page="frm-upcoming.jsp" >
                    <jsp:param name="id" value="<%=u.getId()%>"/>
                </jsp:include>
            </div>
            <hr class="divider"/>
            <div class="calendar">
                <jsp:include page="frm-add-main.jsp"/>
                <h2>Calendar</h2>
                <%--<%= u.getSetting().getA_Time1().toLocalTime() %>--%>
                <jsp:include page="calendar.jsp">
                    <jsp:param name="id" value="<%=u.getId()%>"/>
                </jsp:include>
            </div>
        </div>
        <div class="right" id="right">
            <div class="suggestion">
                <button class="suggs" id="suggs" onclick="switchList()" >Suggestion</button>
                <button class="alt-suggs" id="alt-suggs" onclick="switchList()" >User's notifications</button>
                <div id="sugg-content" class="sugg-content">
                    <jsp:include page="frm-sugg-add.jsp"/>
                </div>
                <div id="notice-list-content" class="notice-list-content" hidden>
                    <jsp:include page="frm-notice-list.jsp">
                        <jsp:param name="id" value="<%=u.getId()%>"/>
                    </jsp:include>
                </div>
            </div>
        </div>
        <script>
            function switchList(){
//                alert("AAAA");
                if(document.getElementById("suggs").innerHTML === "Suggestion"){
                    document.getElementById("suggs").innerHTML = "User's notifications";
                    document.getElementById("alt-suggs").innerHTML = "Suggestion";
                    document.getElementById("sugg-content").hidden = true;
                    document.getElementById("notice-list-content").hidden = false;
                }
                else {
                    document.getElementById("suggs").innerHTML = "Suggestion";
                    document.getElementById("alt-suggs").innerHTML = "User's notifications";
                    document.getElementById("sugg-content").hidden = false;
                    document.getElementById("notice-list-content").hidden = true;
                }
            }
            function showDescr(){
                if (document.getElementById("descrPart").hidden === true){
                    document.getElementById("descrPart").hidden = false;
                }
                else document.getElementById("descrPart").hidden = true;
            }
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
            function unlockSubmit(){
                if(document.getElementById("title").value === null){
                    document.getElementById("add").disabled = true;
                }
                else 
                    document.getElementById("add").disabled = false;
            }
        </script>
    </body>
</html>
