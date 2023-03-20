<%-- 
    Document   : setting
    Created on : 7 Mar 2023, 4:51:36 pm
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.User"  %>
<%@page import = "model.Settings"  %>
<%@page import = "DB.UserDAO"  %>
<%@page import = "DB.SettingsDAO"  %>
<%@page import = "java.util.ArrayList"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/login.css">  
        <title>Settings Page</title>
        <% 
            //TODO CHECK NULL
            if(session.getAttribute("user") == null){response.sendRedirect("login");return;}
            
            UserDAO ud = new UserDAO();
            SettingsDAO sd = new SettingsDAO();
            
            ArrayList<User> ul = ud.getUsers();
            
            User u = new User();
            u =(User)session.getAttribute("user"); 
            boolean check = false;
            for(User v : ul){
                if(v.getName().equals(u.getName()) && v.getPass().equals(u.getPass()))check = true;
            }
            if(!check){response.sendRedirect("login");return;}
            
            Settings s = u.getSetting();
        %>
    </head>
    <body onload="document.getElementById('focus').click()">
        <div class="row">
            <div class="column side">
                <button class="backbutton" onclick="location.href = 'main'">Go Back</button>
                <!--<button class="backbutton" type="button" id="getdate" onclick="getdate()">get date</button>-->
            </div>
            <div class="column middle">
                <form class="form" name="settingForm" action="updateSetting" method="POST" style="margin-top: 60px;">
                    <div class="fhead">Settings</div>
                    <div class="tab">
                        <button form="a" id="focus" class="tablinks" onclick="openCity(event, 'changeUser')">User</button>
                        <button form="a" class="tablinks" onclick="openCity(event, 'changeSetting')">Setting</button>
                    </div>
                    <div id="changeUser" class="tabcontent">
                        <label for="user">Username:</label>
                        <input type="text" name="name" id="user" value="<%=u.getName()%>" required/>
                        <label for="pwd">Password:</label>
                        <input type="password" name="pass" id="pwd" value="<%=u.getPass()%>" required/>
                        <label for="email">E-mail:</label>
                        <input type="email" name="email" id="email" value="<%=u.getEmail()%>" required/>
                        <label for="dob">Date of Birth:</label>
                        <input type="date" name="dob" id="dob" value="<%=u.getDob()%>"/>
                    </div>
                    <div id="changeSetting" class="tabcontent">
                        <label for="qn">Quick Notice:</label>
                        
                        <input type="number" id="qn" name="qn" min="1" onfocusout="unFoReset()" onclick="value5()" value="<%= s.getQ_Notice()%>" placeholder="Default: 5(minutes)"/>    
                        <div class="row qn">
                            <div class="column qn"><input type="radio" name="MMHHdd" value="minutes" id="minutes" <% if(s.getQN_period().equals("minutes")){%>checked="checked" <%}%> onclick="unDef()"/><br><label for="minutes" style="margin: 7px">Minutes</label></div>
                            <div class="column qn"><input type="radio" name="MMHHdd" value="hours" id="hours" <% if(s.getQN_period().equals("hours")){%>checked="checked" <%}%> onclick="unDef()"/><br> <label for="hours" style="margin: 7px">Hours</label></div>
                            <div class="column qn r"><input type="radio" name="MMHHdd" value="days" id="days" <% if(s.getQN_period().equals("days")){%>checked="checked" <%}%> onclick="unDef()"/> <br> <label for="days" style="margin: 7px">Days</label></div>
                        </div>
                        <label for="qn" class="descr">Default timer for your quick notices.</label>
                        <br>
                        <%-- AT1 --%>
                        <label for="at">Alert Time (1):</label>
                        <% if(s.getA_Time1() == null){%>
                        <input type="text" id="at" name="at" placeholder="Default: 7am" style="font-family: inherit;height: 46px;" onfocus="Fo()" onfocusout="unFo()"/>  
                        <% }else { %>
                        <input type="time" id="at" name="at" value="<%= s.getA_Time1()%>" style="font-family: inherit;height: 46px;" onfocus="Fo()" onfocusout="unFo()"/>  
                        <%}%>
                        <label for="at" class="descr">Default alert time for your long term notices.</label>
                        <br>
                        <%-- AT2 --%>
                        <label for="at2">Alert Time (2):</label><br>
                        <% if(s.getA_Time2() == null){%>
                        <input type="text" id="at2" name="at2" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" disabled/>   
                        <% }else { %> 
                        <input type="time" id="at2" name="at2" value="<%= s.getA_Time2()%>" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" />   
                        <%}%>
                        <input type="button" value="Enable" name="disableAT2" id="btAT2" style="width: 16%;height: 46px;float: right;" onclick="enAT(2)"/>
                        <label for="at" class="descr">Default alert time for your long term notices. This is your optional second alert of the day.<i> Order does not matter.</i></label>
                        <br>
                        <%-- AT3 --%>
                        <label for="at3">Alert Time (3):</label><br>
                        <% if(s.getA_Time3() == null){%>
                        <input type="text" id="at3" name="at3" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" disabled/>   
                        <% }else { %> 
                        <input type="time" id="at3" name="at3" value="<%= s.getA_Time3()%>" style="font-family: inherit;width: 83%;height: 46px;" placeholder="Disabled" />   
                        <%}%>  
                        <input type="button" value="Enable" name="disableAT2" id="btAT3" style="width: 16%;height: 46px;float: right;" onclick="enAT(3)"/>
                        <label for="at" class="descr">Default alert time for your long term notices. This is your optional third alert of the day.<i> Order does not matter.</i></label>
                    </div>
                    <input id="submit" type="submit" value="Save"/>
                    <button form="a" class="logout" onclick="logout()">Log Out</button>
                </form>
            </div>
            <div class="column side"></div>
        </div>   
    </body>
    <script>
        function logout() {
            location.href = "Timeout";
        }
        function openCity(evt, cityName) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.className += " active";
        }
    </script> 
    <script>
        function unFoReset() {
            if (document.getElementById("qn").value === null || document.getElementById("qn").value === '') {
                if (document.getElementById("hours").checked === true) {
                    document.getElementById("hours").checked = false;
                    document.getElementById("minutes").checked = true;
                } else if (document.getElementById("days").checked === true) {
                    document.getElementById("days").checked = false;
                    document.getElementById("minutes").checked = true;
                } else
                    document.getElementById("minutes").checked = true;
            }
        }
        function value5() {
            if (document.getElementById("qn").value === null || document.getElementById("qn").value === '') {
                document.getElementById("qn").value = "5";
            }
        }
        function Fo() {
            if (document.getElementById("at").value === null || document.getElementById("at").value === '') {
                document.getElementById("at").type = "time";
                document.getElementById("at").value = "07:00:00";
            }
        }
        function unFo() {
            if (document.getElementById("at").value === null || document.getElementById("at").value === '') {
                document.getElementById("at").type = "text";
                document.getElementById("at").value = null;
                document.getElementById("at").placeholder = "Default: 7am";
            }
        }
        function unDef() {
            document.getElementById("qn").value = "5";
        }
        function enAT(n) {
            if (document.getElementById("at" + n).disabled === true) {
                document.getElementById("at" + n).disabled = false;
                document.getElementById("at" + n).type = "time";
                document.getElementById("btAT" + n).value = "Disable";
            } else {
                document.getElementById("at" + n).disabled = true;
                document.getElementById("at" + n).value = null;
                document.getElementById("at" + n).type = "text";
                document.getElementById("at" + n).placeholder = "Disabled";
                document.getElementById("btAT" + n).value = "Enable";
            }
        }
        function showSettings() {
            var x = document.getElementById("settings");
            if (x.style.display === "none") {
                x.style.display = "block";
                document.getElementById("settingB").value = "Hide Settings";
            } else {
                x.style.display = "none";
                document.getElementById("settingB").value = "Change Settings";
            }
        }
    </script>
    <script>
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear() - 5;

        if (dd < 10) {
            dd = '0' + dd;
        }

        if (mm < 10) {
            mm = '0' + mm;
        }

        today = yyyy + '-' + 12 + '-' + 31;
        document.getElementById("dob").setAttribute("max", today);
    </script>
</html>
