<%-- 
    Document   : forgot.jsp
    Created on : 26 Feb 2023, 3:16:02 pm
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/login.css">
        <%
            String f_action = "f_check";
            String message = null;
            String re = (String)request.getAttribute("returned");
            if(re != null){if(!re.contains("error")){f_action = "forgot";}
            if(re.contains("match")){
                message = "Username and E-mail does not match!";
//                message = re;
            }
            else if (re.contains("user")){
                message = "User account not found!";
                message = re;
            }}
            %>
    </head>
    <body>
        <div class="row">
            <div class="column side">
                <button class="backbutton" onclick="location.href ='login.jsp'">Go Back</button>
            </div>
            <div class="column middle">
                <form class="form" name="loginForm" action="<%=f_action%>" method="POST" <% if(f_action.equals("forgot")){out.print(" style=\"margin-top: 10vh;\"");}%>>
                    <div class="fhead">Forgot password
                        <p style="font-size: 20px;">Enter Username and E-mail to set new password.</p>
                    </div>
                    <% if (message != null){
                          %>
                          <div class ="msg"><%=message%></div>
                    <%  
                        }
                        %>
                    <div>
                        <label for="f_user">Username:</label>
                        <input type="text" name="f_username" id="f_user" placeholder="Username" <% if(f_action.equals("forgot")){ out.print("value=\""+request.getParameter("f_username")+"\" readonly");}%> required/>
                        <label for="f_email">E-mail:</label>
                        <input type="email" name="f_email" id="f_email" placeholder="E-mail" <% if(f_action.equals("forgot")){ out.print("value=\""+request.getParameter("f_email")+"\" readonly");}%> required/>
                    </div>
                    <% if(f_action.equals("forgot")){%>
                    <hr class="line" style="margin-bottom: 30px;margin-top: 30px;">
                    <div>
                        <label for="f_pwd">Password:</label>
                        <input type="password" name="f_password" id="f_pwd" placeholder="Username" onkeyup="matchCheck()" required/>
                        <label for="c_pwd">Confirm Password:</label>
                        <input type="password" name="c_pass" id="c_pwd" placeholder="Username" onkeyup="matchCheck()" required/>
                        <div>
                            <p class="msg" id="msg" style="visibility: hidden">Password Mismatch!</p>
                        </div>
                    </div>
                    <%}%>
                    <input type="submit" <%if(f_action.equals("forgot")){out.print("value=\"Reset Password\" disabled=\"disabled\"");}else {out.print("value=\"Find Account\"");}%>" name="find" id="find"/>
                </form>
            </div>
            <div class="column side"></div>
        </div>
        <script>
            function matchCheck(){
                var p1 = document.getElementById("f_pwd").value;
                var p2 = document.getElementById("c_pwd").value;
                
                if(p1 != p2){
                    document.getElementById("msg").style.visibility = "initial";
                    document.getElementById("find").setAttribute("disabled",true);
                }
                else {document.getElementById("msg").style.visibility = "hidden";
                    document.getElementById("find").removeAttribute("disabled");
                }
            }
        </script>
    </body>
</html>
