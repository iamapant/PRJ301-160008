<%-- 
    Document   : calendar
    Created on : Mar 20, 2023, 4:24:49 PM
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/calendar.css">
    </head>
    <body>
        <%
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DATE, 1);
        %>
        <div class="monthD">
            <fmt:formatDate var="month" value="<%=c.getTime()%>" pattern="MM"/>
            <fmt:formatDate var="year" value="<%=c.getTime()%>" pattern="yyyy"/>
            <h1 class="h1">${month} ${year}</h1>
        </div>
        <table class="table">
            <thead class="thead">
                <tr class="tr">
                    <th class="th" style="background: #ff4444;color: white">SUN</th>
                    <th class="th">MON</th>
                    <th class="th">TUE</th>
                    <th class="th">WED</th>
                    <th class="th">THU</th>
                    <th class="th">FRI</th>
                    <th class="th">SAT</th>
                </tr>
            </thead>
            <tbody class="tbody">
                <c:set var="i" value="1" />
                <tr class="tr">
                    <c:set var="dow" value="1" />
                    <c:forEach var="j" begin="1" end="<%= c.get(Calendar.DAY_OF_WEEK) - 1%>">
                        <c:choose>
                            <c:when test="${j == 1}">
                                <td class="td" style="background: #ddd" id="front" onclick="open_stuff(this.id)"></td>
                            </c:when>
                            <c:otherwise>
                                <td class="td" id="front" onclick="open_stuff(this.id)"></td>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="dow" value="${dow+1}" />
                    </c:forEach>
                    <c:forEach begin="<%= c.get(Calendar.DAY_OF_WEEK)%>" end="7">
                        <c:choose>
                            <c:when test="${dow == 1}">
                                <td class="td" style="background: #ddd" id="${i}" onclick="open_stuff(this.id)">${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td class="td" id="${i}" onclick="open_stuff(this.id)">${i}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="i" value="${i+1}"/> 
                        <c:set var="dow" value="${dow+1}" />
                    </c:forEach>
                </tr>
                <tr class="tr">
                    <c:set var="dow" value="1" />
                    <c:forEach begin="${i}" end="<%=c.getActualMaximum(Calendar.DATE)%>" >
                        <c:if test="${dow == 8}">
                            <c:set var="dow" value="${1}"/>
                        </tr><tr class="tr">
                        </c:if>
                        <c:choose>
                            <c:when test="${dow == 1}">
                                <td class="td" id="${i}" style="background: #ddd" onclick="open_stuff(this.id)"> ${i}</td>
                                <c:set var="i" value="${i+1}"/>
                            </c:when>
                            <c:otherwise>
                                <td class="td" id="${i}" onclick="open_stuff(this.id)"> ${i}</td>
                                <c:set var="i" value="${i+1}"/>
                            </c:otherwise>
                        </c:choose>
                        <c:set var="dow" value="${dow+1}"/>
                    </c:forEach>
                    <c:forEach begin="${dow}" end="7">
                        <td class="td" id="back" onclick="open_stuff(this.id)"></td>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
        <script>
            function open_stuff(id) {
                alert(id);
            }
        </script>
    </body>
</html>
