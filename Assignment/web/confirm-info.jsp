<%-- 
    Document   : confirm-info
    Created on : 26 Feb 2023, 11:51:56 am
    Author     : iamap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="model.Settings" %>
<%@page import="java.util.ArrayList" %>
<%@page import="DB.UserDAO" %>
<%@page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/cinfo.css">
    </head>
    <body>
        <% 
//        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("createUser");
        
//        request.setAttribute("createUser", u);
        request.setAttribute("cinfo", "true");
        //TODO CHECK NULL
        UserDAO ud = new UserDAO();
        ArrayList<User> ul = ud.getUsers();
        boolean checkIn = false;
//        for(User v : ul){
//            if(v.equals(u))checkIn = true;
//        }
        if(u == null)request.getRequestDispatcher("login.jsp").forward(request,response);
        
        %>
        <div class="row">
            <div class="column side">
                <!--<button class="backbutton" onclick="location.href ='./'">Go Back</button>-->
            </div>
            <div class="column middle">
                <form class="form" name="loginForm" action="confirmed" method="POST">
                    <div class="fhead">Confirm User Information</div>
                    <div>
                        <label for="user">Username:</label>
                        <p id="user"><%= u.getName() %></p>
                    </div><div>
                        <label for="email">E-Mail:</label>
                        <p id="email"><%= u.getEmail() %></p>
                    </div><div>
                        <label for="dob">Date of Birth:</label>
                        <p id="dob"><% if(u.getDob() != null){out.print(u.getDob());}else{out.print("(Date of birth is not set.)");} %></p>
                    </div><div>
                        <label for="qn">Quick_Notices timer:</label>
                        <p id="qn"><% if(u.getSetting().getQ_Notice() != null ){out.print(u.getSetting().getQ_Notice());}else{out.print("(Default: 5 minutes)");} %></p>
                    </div><div>
                        <label for="at">Alert 1 time:</label>
                        <p id="at"><% if(u.getSetting().getA_Time1() != null){out.print(u.getSetting().getA_Time1());}else{out.print("(Default: 7AM)");} %></p>
                    </div><div>
                        <label for="at2">Alert 2 time:</label>
                        <p id="at2"><% if(u.getSetting().getA_Time2() != null){out.print(u.getSetting().getA_Time2());}else{out.print("(Not set)");} %></p>
                    </div><div>
                        <label for="at3">Alert 3 time:</label>
                        <p id="at3"><% if(u.getSetting().getA_Time3() != null){out.print(u.getSetting().getA_Time3());}else{out.print("(Not set)");} %></p>
                    </div>
                    <input type="submit" style="width: 48%; float: right;" value="Confirm"/>
                </form>
            </div>
            <div class="column side"></div>
        </div>
    </body>
</html>
