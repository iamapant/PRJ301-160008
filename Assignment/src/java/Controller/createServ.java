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
import java.util.ArrayList;
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
            Settings f = new Settings();
//            f.setA_Time1(Time.valueOf(request.getParameter("at")));
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createServ</title>");    
//            out.println("<p>"+Time.valueOf(parse)+"</p>");
            out.println("<p>"+request.getParameter("MMHHdd")+"</p>");
            out.println("<p>"+request.getParameter("at")+"</p>");
//            out.println("<p>"+Time.valueOf(request.getParameter("at"))+"</p>");
//            out.println("<p>"+f.getA_Time1()+"</p>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            return;
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
//        processRequest(request, response);

        UserDAO ud = new UserDAO();
        ArrayList<User> ul = ud.getUsers();
        if(!(request.getParameter("cinfo") == null || request.getParameter("cinfo").length() == 0) && request.getAttribute("createUser") != null){
            ud.insertUser((User)request.getAttribute("createUser"));
            response.sendRedirect("index.html");
        }
        
        //TODO CHECK NULL
        boolean isName = false;
        boolean isMail = false;
        for(User user : ul){
             if(user.getName().equals(request.getParameter("username")) || user.getEmail().equals(request.getParameter("username"))) isName = true;
             if(user.getName().equals(request.getParameter("email")) || user.getEmail().equals(request.getParameter("email"))) isMail = true;
        }
        
        String msg = "";
        if(isName)msg += ".user";
        if(isMail)msg += ".mail";
        if(!msg.equals("")){
            request.setAttribute("createMessage", msg);
            request.getRequestDispatcher("create.jsp").forward(request, response);
            return;
        }
        
        User u = new User();
        
        u.setName(request.getParameter("username"));
        u.setEmail(request.getParameter("email"));
        u.setPass(request.getParameter("password"));
        
//        User v = ud.getUserByLogin(u.getName(), u.getPass());

        
        u.setDob((Date)request.getAttribute("dob"));
        Settings s = new Settings();
        
        String qnGet = request.getParameter("qn");
        String qnMul = request.getParameter("MMHHdd");
        int qn = 0;
        if(qnGet != null && qnGet.matches("\\d+")){
            if(qnMul.equals("minutes"))qn = 1;
            else if(qnMul.equals("hours"))qn = 60;
            else if(qnMul.equals("days"))qn = 60*24;
            qn *= Integer.parseInt(qnGet);
            qnGet = qn/60+":"+qn%60+":00";
        }
        if (!(qnGet == null || qnGet.length() == 0))s.setQ_Notice((Time.valueOf(qnGet)));
        
        if(!(request.getParameter("at") == null || request.getParameter("at").length() == 0))s.setA_Time1(Time.valueOf(request.getParameter("at")+":00"));
        if(!(request.getParameter("at2") == null || request.getParameter("at2").length() == 0))s.setA_Time2(Time.valueOf(request.getParameter("at2")+":00"));
        if(!(request.getParameter("at3") == null || request.getParameter("a3").length() == 0))s.setA_Time3(Time.valueOf(request.getParameter("at3")+":00"));
        
        u.setSetting(s);
        
        HttpSession session = request.getSession();
        session.setAttribute("createUser", u);
        
        
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
        if(getQN != "00:00");
        else if(request.getParameter("MMHHdd").equals("minutes")){
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
