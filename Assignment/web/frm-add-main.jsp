<%-- 
    Document   : frm-add-main
    Created on : 11 Mar 2023, 11:41:29 pm
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="addNoti" action="addNoti" method="POST">
            <div class="header" style="padding-bottom: 10px;">Create new notification</div>
            <div class="grid-container">
                <div class="timePart">
                    <c:choose>
                        <c:when test="${empty requestScope.date}">
                            <c:set var="now" scope="page" property="date" value=""/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="now" scope="page" property="date" value="${requestScope.date}"/>
                        </c:otherwise>
                    </c:choose>
                    <fmt:formatDate type = "date" pattern="yyyy-dd-MM" value = "${now}" var="date2Add"/>
                    <label for="date">on: </label>
                    <input type="date" name="date" id="date" value="${date2Add}"/>
                    <c:choose>
                        <c:when test="${empty requestScope.time}">
                            <c:set var="nowT" scope="page" property="date" value=""/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="nowT" scope="page" property="time" value="${requestScope.time}"/>
                        </c:otherwise>
                    </c:choose>
                    <fmt:formatDate type = "time" pattern="HH:mm" value = "${nowT}" var="time2Add"/>
                    <label for="time"> at: </label>
                    <input type="time" name="time" id="time" value="${time2Add}" />
                </div>
                <div class="infoPart">
                    <label for="title">    About: </label>
                    <input type="text" name="title" value="" required/>
                    <input type="submit" value="Add" id="add" name="add" />
                </div>
                <div class="miscPart">
                    <label for="repeat">Repeat: </label>
                    <select name="repeat">
                        <option>(none)</option>
                        <option>Daily</option>
                        <option>Weekly</option>
                        <option>Bi-weekly</option>
                        <option>Monthly</option>
                        <option>Yearly</option>
                    </select>
                    <input type="button" value="Description" onclick="showDescr()" />
                </div>
            </div>
            <div class="descrPart" id="descrPart" hidden>
                <textarea name="descr" maxlength="200" wrap="soft" style="width: 90%;resize: none;height: 30px"></textarea>
            </div>
        </form>
    </body>
</html>
