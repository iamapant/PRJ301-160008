/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DB.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import static java.util.Objects.nonNull;
import model.Settings;
import model.User;

/**
 *
 * @author iamap
 */
public class createServ extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createServ</title>");    
//            out.println("<p>"+Time.valueOf(parse)+"</p>");
            out.println("<p>"+request.getParameter("MMHHdd")+"</p>");
            out.println("<p>"+request.getParameter("at")+"</p>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
//            return;
        }
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
//        processRequest(request, response);
            response.sendRedirect("create.jsp");
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
        processRequest(request, response);
        
        UserDAO ud = new UserDAO();
        User u = new User();
        
        u.setName(request.getParameter("username"));
        u.setEmail(request.getParameter("email"));
        u.setPass(request.getParameter("password"));
        
//        User v = ud.getUserByLogin(u.getName(), u.getPass());

        //TODO CHECK NULL






















        if(ud.getUserByName(u.getName()).isEmpty()){
            request.setAttribute("createMessage", "user");
            if(ud.getUserByName(u.getEmail()).isEmpty()){
                request.setAttribute("createMessage", "user.email"+ud.getUserByLogin(u.getName(), u.getPass()).getName());
                request.getRequestDispatcher("create.jsp").forward(request, response);
            }
            request.getRequestDispatcher("create.jsp").forward(request, response);
        }if(ud.getUserByName(u.getEmail()).isEmpty()){
            request.setAttribute("createMessage", "email");
            request.getRequestDispatcher("create.jsp").forward(request, response);
        }
        
        u.setDob((Date)request.getAttribute("dob"));
        Settings s = new Settings();
        int qn = Integer.parseInt(request.getParameter("qn"));
        
        s.setQ_Notice((Time.valueOf(getQNString(request,response))));
        s.setA_Time1(Time.valueOf(request.getParameter("at")));
        s.setA_Time1(Time.valueOf(request.getParameter("at2")));
        s.setA_Time1(Time.valueOf(request.getParameter("at3")));
        
        u.setSetting(s);
        
        request.setAttribute("createUser", u);
        request.getRequestDispatcher("confirm-info").forward(request, response);
//        ud.insertUser(u);
        
//        response.sendRedirect("login");
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

    private String getQNString(HttpServletRequest request, HttpServletResponse response) {
        String getQN = request.getParameter("qn");
        String parse = "";
        if(request.getParameter("MMHHdd").equals("minutes")){
            int Iqn = Integer.valueOf(getQN);
            int qnH = 0;
            if(Iqn >= 60){
                qnH = Iqn / 60;
                Iqn = Iqn % 60;
            }
            //get String
            parse = "0"+qnH+":";
            //minutes
            if(Iqn < 10){
                parse += "0"+Iqn+":00";
            }
            else parse += Iqn+":00";
        }
        else if(request.getParameter("MMHHdd").equals("hours")){
            if(getQN.length() == 2)parse = getQN+":00:00";
            if(getQN.length() == 1)parse = "0"+getQN+":00:00";
        }
        else if(request.getParameter("MMHHdd").equals("days")){
            int Iqn = Integer.valueOf(getQN);
            Iqn *= 24;
            parse = Iqn+":00:00";
        }
        if(parse.length() >= 7)return parse;
        else return null;
    }

}
