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
            if(session.getAttribute("user") == null){response.sendRedirect("login");return;}
            
            UserDAO ud = new UserDAO();
            NoticesDAO nd = new NoticesDAO();
            SugNoticeDAO sd = new SugNoticeDAO();
            
            ArrayList<User> ul = ud.getUsers();
            ArrayList<SugNotice> sl = sd.getSugNotices();
            
            
            User u = new User();
            u =(User)session.getAttribute("user"); 
            boolean check = false;
            for(User v : ul){
                if(v.getName().equals(u.getName()) && v.getPass().equals(u.getPass()))check = true;
            }
            if(!check){response.sendRedirect("login");return;}
            ArrayList<Notice> nl = nd.getUpcomingNotices(u);
        %>
    </head>
    <body>
        <div class="ribbon">
            <div class="setting">
                <input type="submit" value="☰" class="settingBtn" id="settingBtn" onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = 'transparent'" onclick="showRight()"/>
                <img src="pics/icon.jpg" class="icon" onclick="location.href ='setting.jsp'" onmouseover="this.style.border = '6px solid white'" onmouseout="this.style.border = '6px solid transparent'">
            </div>
        </div>
        <div class="left" id="left">
            <div class="upcoming">
                <h2>Upcoming</h2>
                <c:set var="num_Notice" scope="page" value="<%=nl.size()%>"/>
                <c:choose>
                    <c:when test="${num_Notice == 1}">
                        <div class="grid-container">
                            <div class="grid-child">
                                <h2><%=nl.get(0).getTitle()%></h2>
                                <% if(nl.get(0).getDescr() != null ) nl.get(0).getDescr();%>
                                <%=nl.get(0).getA_Time()%>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${num_Notice == 2}">
                        <div class="grid-container">
                            <div class="grid-child">
                                <%=nl.get(0).getTitle()%>
                                <% if(nl.get(0).getDescr() != null ) nl.get(0).getDescr();%>
                                <%=nl.get(0).getA_Time()%>
                            </div>
                            <div class="grid-child">
                                <%=nl.get(1).getTitle()%>
                                <% if(nl.get(1).getDescr() != null ) nl.get(1).getDescr();%>
                                <%=nl.get(1).getA_Time()%>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${num_Notice >= 3}">
                        <div class="grid-container">
                            <div class="grid-child">
                                <%=nl.get(0).getTitle()%>
                                <% if(nl.get(0).getDescr() != null ) nl.get(0).getDescr();%>
                                <%=nl.get(0).getA_Time()%>
                            </div>
                            <div class="grid-child">
                                <%=nl.get(1).getTitle()%>
                                <% if(nl.get(1).getDescr() != null ) nl.get(1).getDescr();%>
                                <%=nl.get(1).getA_Time()%>
                            </div>
                            <div class="grid-child">
                                <%=nl.get(2).getTitle()%>
                                <% if(nl.get(2).getDescr() != null ) nl.get(2).getDescr();%>
                                <%=nl.get(2).getA_Time()%>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="no-notices">
                        You have no upcoming notices!
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="calendar">
                <jsp:include page="frm-add-main.jsp"/>
                <h2>Calendar</h2>
                <%= u.getSetting().getA_Time1().toLocalTime() %>
            </div>
        </div>
        <div class="right" id="right">
            <div class="suggestion">
                <h1 id="suggs" onclick="switchList()" style="margin-bottom: 5px">Suggestion</h1>
                <h4 id="alt-suggs" onclick="switchList()" style="color: #cccccc;margin-top: 5px">User's notifications</h4>
                <jsp:include page="frm-sugg-add.jsp"/>
            </div>
        </div>
        <script>
            function switchList(){
//                alert("AAAA");
                if(document.getElementById("suggs").innerHTML === "Suggestion"){
                    document.getElementById("suggs").innerHTML = "User's notifications";
                    document.getElementById("alt-suggs").innerHTML = "Suggestion";
                }
                else {
                    document.getElementById("suggs").innerHTML = "Suggestion";
                    document.getElementById("alt-suggs").innerHTML = "User's notifications";
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
