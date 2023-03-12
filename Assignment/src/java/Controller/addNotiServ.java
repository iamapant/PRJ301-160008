/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DB.NoticesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Notice;
import model.Settings;
import model.User;

/**
 *
 * @author iamap
 */
public class addNotiServ extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("user");
//        if(u == null){response.sendRedirect("login");return;}
        
//        String timeS = request.getParameter("date") +" " + request.getParameter("time")+":00";
//        Timestamp time = Timestamp.valueOf(timeS);
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addNotiServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addNotiServ at " + new Date(getDateWithoutTime().getTime()) + "</h1>");
            out.println("<h1>Servlet addNotiServ at " + u.getName() + "</h1>");
            out.println("<h1>Servlet addNotiServ at " + "aaa" + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("user") == null) {response.sendRedirect("login");return;}
        
        User u = (User)session.getAttribute("user");
        int id = u.getId();
        String title = request.getParameter("title");
        String descr = request.getParameter("desrc");
        String repS = request.getParameter("repeat");
        String dateS = request.getParameter("date");
        Date date;
        
        long timeLong = 0, timeLong2 = 0, timeLong3 = 0;
        if(dateS.length() != 0) date = Date.valueOf(dateS); else date = getDateWithoutTime();
        timeLong += date.getTime();
        
        
        int rep = 0;
        if("Daily".equals(repS))rep = 1;
        else if("Weekly".equals(repS))rep = 2;
        else if("Bi-weekly".equals(repS))rep = 3;
        else if("Monthly".equals(repS))rep = 4;
        else if("Yearly".equals(repS))rep = 5;
        
        if(request.getParameter("time").length() != 0){
            String[] timeS = request.getParameter("time").split(":");
            timeLong += Integer.parseInt(timeS[0])*1000*60*60;
            timeLong += Integer.parseInt(timeS[1])*1000*60;
        }
        else{
            Date tomorrow = new Date(System.currentTimeMillis() + 1000*60*60*24);
            if(date.compareTo(tomorrow) >= 0){
                String[] timePart = u.getSetting().getA_Time1().toString().split(":");
                timeLong += Integer.parseInt(timePart[0])*1000*60*60 + Integer.parseInt(timePart[1])*1000*60;
                if(u.getSetting().getA_Time2() != null){
                    timePart = u.getSetting().getA_Time2().toString().split(":");
                    timeLong2 += date.getTime() + Integer.parseInt(timePart[0])*1000*60*60 + Integer.parseInt(timePart[1])*1000*60;
                }
                if(u.getSetting().getA_Time3() != null){
                    timePart = u.getSetting().getA_Time3().toString().split(":");
                    timeLong2 += date.getTime() + Integer.parseInt(timePart[0])*1000*60*60 + Integer.parseInt(timePart[1])*1000*60;
                }
            }
            else {
                String[] timePart = u.getSetting().getQ_Notice().toString().split(":");
                timeLong = Integer.parseInt(timePart[0])*1000*60*60 + Integer.parseInt(timePart[1])*1000*60 + System.currentTimeMillis();
            }
        }
        if(new Date(timeLong).after(new Date(System.currentTimeMillis()))){
            Notice n = new Notice();
            n.setId(id);
            n.setTitle(title);
            if(descr != null)n.setDescr(descr);
            n.setRepeatable(rep);
            n.setA_Time(new Timestamp(timeLong));
            NoticesDAO nd = new NoticesDAO();
            nd.insertNotice(n);
            
            if(timeLong2 != 0){n.setA_Time(new Timestamp(timeLong2));nd.insertNotice(n);}
            if(timeLong3 != 0){n.setA_Time(new Timestamp(timeLong3));nd.insertNotice(n);}
        }
        
        response.sendRedirect("main");
        return;
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

    private java.sql.Date getDateWithoutTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new Date(c.getTime().getTime());
    }

}
