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
    <body>
        <% 
            UserDAO ud = new UserDAO();
            NoticesDAO nd = new NoticesDAO();
            
            User u = ud.getUser(Integer.parseInt(request.getParameter("id")));
            ArrayList<Notice> full_nl = nd.getNotices(u);
            
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm");
            
        if(full_nl.size() != 0){
            for (int i = 0; i < full_nl.size(); i++){
        %>
        <c:set var="rep" value="<%=full_nl.get(i).getRepeatable()%>"/>
        <div class="list-child">
            <input class="inner" style="float: right;" type="button" onclick="location.href = 'delete?id=<%=full_nl.get(i).getNid()%>'" value="Delete"/>
            <h3 class="inner" style="margin-bottom: 10px;margin-top: 5px;"><%= full_nl.get(i).getTitle()%></h3>
            <div class="inner">ID: <b><%= full_nl.get(i).getNid()%></b></div>
            <div class="inner">Alert date: <b><%= date.format(full_nl.get(i).getA_Time())%></b></div>
            <div class="inner">Alert time: <b><%= time.format(full_nl.get(i).getA_Time())%></b></div>
            <div class="inner">
                Repeat: <b>
                    <c:choose>
                        <c:when test="${rep == 0}">
                            None
                        </c:when>
                        <c:when test="${rep == 1}">
                            Daily
                        </c:when>
                        <c:when test="${rep == 2}">
                            Weekly
                        </c:when>
                        <c:when test="${rep == 3}">
                            Bi-Weekly
                        </c:when>
                        <c:when test="${rep == 4}">
                            Monthly
                        </c:when>
                        <c:when test="${rep == 5}">
                            Yearly
                        </c:when>
                        <c:otherwise>
                            ????
                        </c:otherwise>
                    </c:choose>
                </b>
            </div>
            <div class="inner">Description: <b><% if(full_nl.get(i).getDescr() != null)out.print(full_nl.get(i).getDescr()); else out.print("None"); %></b> </div>
        </div>
        <%}}
        else { %>
        <div class="no-notices">
            You have no notifications!
        </div>
        <%}%>
    </body>
</html>
