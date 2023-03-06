package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import DB.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author iamap
 */
public class loginServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check session for alr login
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        if(session.getAttribute("user") != null){
            User u = (User) session.getAttribute("user");
            if(ud.getUserByLogin(u.getName(), u.getPass()) != null){
                session.setAttribute("user", u);
                request.getRequestDispatcher("main").forward(request, response);
                return;
            }
        }
        //check cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            String username = null;
            String password = null;
            for(Cookie c : cookies){
                if(c.getName().equals("username"))username = c.getValue();
                if(c.getName().equals("password"))password = c.getValue();
            }
            if(username != null && password != null){
                User u = ud.getUserByLogin(username, password);
                if(u == null)response.sendRedirect("login.jsp");
                session.setAttribute("user", u);
                request.getRequestDispatcher("main").forward(request, response);
                return;
            }
        }
        //goto login page if both null
//        else 
            response.sendRedirect("login.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        HttpSession session = request.getSession();
        
        UserDAO ud = new UserDAO();
        ArrayList<User> ul = ud.getUsers();
        User u = null;
        for(User v : ul){
            if(v.getName().equals(user) && v.getPass().equals(pass)){u = v; break;}
        }
        
        if(u != null){
            session.setAttribute("user", u);
            if(request.getParameter("rememberme") != null) {
                Cookie username = new Cookie("username", user);
                Cookie password = new Cookie("password", pass);
                username.setMaxAge(3600*24*7*365);
                password.setMaxAge(3600*24*7*365);
                response.addCookie(username);
                response.addCookie(password);
            }
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
        else {
            request.setAttribute("errorMessage", "Username or password incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
//            response.sendRedirect("login");
        }

//        response.sendRedirect("index.html");
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
