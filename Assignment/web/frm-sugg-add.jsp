<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Notice"  %>
<%@page import = "model.SugNotice"  %>
<%@page import = "DB.NoticesDAO"  %>
<%@page import = "DB.SugNoticeDAO"  %>
<%@page import = "java.util.ArrayList"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            SugNoticeDAO sd = new SugNoticeDAO();
            ArrayList<SugNotice> sl = sd.getSugNotices();
            
        %>
    <c:set var="suggestion" value="<%= sl%>"/>
    <c:choose>
        <c:when test="${not empty suggestion}">
            <div class="suggs">
                <c:set value="<%=sl.get(0).getAlert_Date()%>" var="date" scope="request" />
                ${date}
                <form name="addNoti" action="addNoti" method="POST">
                    <input type="text" name="title" placeholder="<%=sl.get(0).getTitle()%>" />
                    <br>
                    <textarea name="desrc" rows="4" cols="20" style="resize: none"></textarea>
                    <br>
                    <input type="time" name="time" value="" />
                    <input type="submit" value="Add" />
                    <input type="date" name="date" value="${date}" hidden readonly/>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-notices">You have no suggested notifications!</div>
        </c:otherwise>
    </c:choose>
</body>
</html>
