<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.User"  %>
<%@page import = "DB.UserDAO"  %>
<%@page import = "model.Notice"  %>
<%@page import = "DB.NoticesDAO"  %>
<%@page import = "java.util.ArrayList"  %>
<%@page import = "java.text.SimpleDateFormat"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="setdiv();switchList()">
        <%
            UserDAO ud = new UserDAO();
            NoticesDAO nd = new NoticesDAO();
            
            User u = ud.getUser(Integer.parseInt(request.getParameter("id")));
            ArrayList<Notice> nl = nd.getUpcomingNotices(u);
            
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
            
            int i = 0;
        %>
        <c:set var="num_Notice" scope="page" value="<%=nl.size()%>"/>
        <c:if test="${num_Notice > 3}">
            <c:set var="num_Notice" value="${3}"/>
        </c:if>
        <c:choose>
            <c:when test="${num_Notice != 0}">
                <div id="gettime" hidden><%=nl.get(0).getA_Time().getTime()%></div>
                <div id="gettitle" hidden><%=nl.get(0).getTitle()%></div>
                <div id="getdescr" hidden><%=nl.get(0).getDescr()%></div>
                <div class="grid-container">
                    <c:forEach var="t" begin="0" end="${num_Notice - 1}">
                        <div class="grid-child">
                            <h2 class="inner" style="margin-bottom: 10px;margin-top: 5px;"><%=nl.get(i).getTitle()%></h2>
                            <div><% if(nl.get(i).getDescr() != null ) out.print("Description: "+nl.get(i).getDescr());%></div>
                            <div>Date: <%=date.format(nl.get(i).getA_Time())%></div>
                            <div>Time: <%=time.format(nl.get(i++).getA_Time())%></div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="no-notices">
                    You have no upcoming notifications!
                </div>
            </c:otherwise>
        </c:choose>
                <div id="reset" hidden></div>
    </body>
    <script>
        function setdiv(){
            var getS = document.getElementById("gettime").innerHTML;
            var now = new Date().getTime();
            var descr = document.getElementById("getdescr").innerHTML;
            if (descr.length !== 0){
                descr = "\n\nDescription: " + descr;
            }
            setTimeout(function(){alert("Notification: " + document.getElementById("gettitle").innerHTML + descr);window.location.reload();},getS-now);
        }
    </script>
</html>
