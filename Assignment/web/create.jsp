<%-- 
    Document   : create
    Created on : 25 Feb 2023, 5:59:29 pm
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.Cookie" %>
<%@page import="jakarta.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/login.css">
        <title>JSP Page</title>
        <% String em = (String)request.getAttribute("createMessage");
        session.invalidate();
        Cookie[] cookie = request.getCookies();
        for(Cookie c : cookie){
            c.setMaxAge(0);
        } %>
    </head>
    <body>
        <div class="row">
            <div class="column side"><button class="backbutton" onclick="location.href ='login.jsp'">Go Back</button></div>
            <div class="column middle">
                <form class="form" name="loginForm" action="create" method="POST" style="margin-top: 60px;">
                    <div class="fhead">Create user</div>
                    <br>
                    <label for="username">Username:</label>
                    <input type="text" name="username" id="username" placeholder="Username" autofocus required />
                    <% if(em != null) if(em.contains("user")){
                    %><div id="status" class="status" style="visibility:visible;width: 100%;height:40px;display: inline-block"><p class="msg">Username is already taken.</p></div><%
                        }%>
                    <label for="mail">E-mail:</label>
                    <input type="email" name="email" id="mail" placeholder="E-mail" required/>
                    <% if(em != null) if(em.contains("mail")){
                    %><div id="status" class="status" style="visibility:visible;width: 100%;height:40px;display: inline-block"><p class="msg">E-mail is already used.</p></div><%
                        }%>
                    <label for="pwd">Password:</label>
                    <input type="password" name="password" id="pwd" placeholder="Password" required/>
                    <label for="dob">Date of Birth:</label>
                    <input type="date" id="dob" name="dob" />
                    <input type="submit" value="Create" name="create" />
                    <br><br>
                    <hr class="divider">
                    <br>
                    <input type="button" value="Change Settings" id="settingB" onclick="showSettings()" style="margin-bottom: 20px;"/>
                    <div id="settings" style="display: none;">
                        <label for="qn">Quick Notice:</label>
                        <input type="number" id="qn" name="qn" min="1" onfocusout="unFoReset()" onclick="value5()" <% if(request.getParameter("qn") != null){%> value="<%= request.getParameter("qn")%>"<%}%> placeholder="Default: 5(minutes)"/>    
                        <div class="row qn">
                            <div class="column qn"><input type="radio" name="MMHHdd" value="minutes" id="minutes" <% if(request.getParameter("MMHHdd") == null ||request.getParameter("MMHHdd").equals("minutes")){%>checked="checked" <%}%> onclick="unDef()"/><br><label for="minutes" style="margin: 7px">Minutes</label></div>
                            <div class="column qn"><input type="radio" name="MMHHdd" value="hours" id="hours" <% if(request.getParameter("MMHHdd") != null &&request.getParameter("MMHHdd").equals("hours")){%>checked="checked" <%}%> onclick="unDef()"/><br> <label for="hours" style="margin: 7px">Hours</label></div>
                            <div class="column qn r"><input type="radio" name="MMHHdd" value="days" id="days" <% if(request.getParameter("MMHHdd") != null &&request.getParameter("MMHHdd").equals("days")){%>checked="checked" <%}%> onclick="unDef()"/> <br> <label for="days" style="margin: 7px">Days</label></div>
                        </div>
                        <label for="qn" class="descr">Default timer for your quick notices.</label>
                        <br>
                        <%-- AT1 --%>
                        <label for="at">Alert Time (1):</label>
                        <% if(request.getParameter("at") == null){%>
                        <input type="text" id="at" name="at" placeholder="Default: 7am" style="font-family: inherit;height: 46px;" onfocus="Fo()" onfocusout="unFo()"/>  
                        <% }else { %>
                        <input type="time" id="at" name="at" value="<%= request.getParameter("at")%>" style="font-family: inherit;height: 46px;" onfocus="Fo()" onfocusout="unFo()"/>  
                        <%}%>
                        <label for="at" class="descr">Default alert time for your long term notices.</label>
                        <br>
                        <%-- AT2 --%>
                        <label for="at2">Alert Time (2):</label><br>
                        <% if(request.getParameter("at2") == null || request.getParameter("at2").length() == 0){%>
                        <input type="text" id="at2" name="at2" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" disabled/>   
                        <% }else { %> 
                        <input type="time" id="at2" name="at2" value="<%= request.getParameter("at2")%>" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" />   
                        <%}%>
                        <input type="button" value="Enable" name="disableAT2" id="btAT2" style="width: 16%;height: 46px;float: right;" onclick="enAT(2)"/>
                        <label for="at" class="descr">Default alert time for your long term notices. This is your optional second alert of the day.<i> Order does not matter.</i></label>
                        <br>
                        <%-- AT3 --%>
                        <label for="at3">Alert Time (3):</label><br>
                        <% if(request.getParameter("at3") == null || request.getParameter("at3").length() == 0){%>
                        <input type="text" id="at3" name="at3" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" disabled/>   
                        <% }else { %> 
                        <input type="time" id="at3" name="at3" value="<%= request.getParameter("at3")%>" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" />   
                        <%}%>  
                        <input type="button" value="Enable" name="disableAT2" id="btAT3" style="width: 16%;height: 46px;float: right;" onclick="enAT(3)"/>
                        <label for="at" class="descr">Default alert time for your long term notices. This is your optional third alert of the day.<i> Order does not matter.</i></label>
                    </div>
                </form>
            </div>
            <div class="column side"></div>
        </div>        
        <script>
            function unFoReset(){
                if(document.getElementById("qn").value === null || document.getElementById("qn").value === ''){
                    if(document.getElementById("hours").checked === true){
                        document.getElementById("hours").checked = false;
                    document.getElementById("minutes").checked = true;
                    }
                    else if(document.getElementById("days").checked === true){
                        document.getElementById("days").checked = false;
                    document.getElementById("minutes").checked = true;
                    }
                    else document.getElementById("minutes").checked = true;
                }
            }
            function value5(){
                if(document.getElementById("qn").value === null || document.getElementById("qn").value === ''){
                    document.getElementById("qn").value = "5";
                }
            }
            function Fo(){
                if(document.getElementById("at").value === null || document.getElementById("at").value === ''){
                    document.getElementById("at").type = "time";
                    document.getElementById("at").value = "07:00:00";
                }
            }
            function unFo(){
                if(document.getElementById("at").value === null || document.getElementById("at").value === ''){
                    document.getElementById("at").type = "text";
                    document.getElementById("at").value = null;
                    document.getElementById("at").placeholder = "Default: 7am";
                }
            }
            function unDef(){
                document.getElementById("qn").value = "5";
            }
            function enAT(n){
                if(document.getElementById("at"+n).disabled === true){
                    document.getElementById("at"+n).disabled = false;
                    document.getElementById("at"+n).type = "time";
                    document.getElementById("btAT"+n).value = "Disable";
                }
                else{
                    document.getElementById("at"+n).disabled = true;
                    document.getElementById("at"+n).value = null;
                    document.getElementById("at"+n).type = "text";
                    document.getElementById("at"+n).placeholder = "Disabled";
                    document.getElementById("btAT"+n).value = "Enable";
                }
            }
            function showSettings(){
                var x = document.getElementById("settings");
                if(x.style.display === "none"){
                    x.style.display = "block";
                    document.getElementById("settingB").value = "Hide Settings";
                }
                else {
                    x.style.display = "none";
                    document.getElementById("settingB").value = "Change Settings";
                }
            }
        </script>
        <script>
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!
            var yyyy = today.getFullYear()-5;

            if (dd < 10) {
               dd = '0' + dd;
            }

            if (mm < 10) {
               mm = '0' + mm;
            } 

            today = yyyy + '-' + 12 + '-' + 31;
            document.getElementById("dob").setAttribute("max", today);
        </script>
    </body>
</html>
