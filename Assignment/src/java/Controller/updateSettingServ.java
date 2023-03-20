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
import model.Settings;
import model.User;

/**
 *
 * @author iamap
 */
public class updateSettingServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String n)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateSetting</title>");
            out.println("</head>");
            out.println("<body onload=\"setTimeout(function(){document.location = 'setting'}, 3000)\">");
            out.println("<h1>" + n + "</h1>");
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
//        processRequest(request, response);
        response.sendRedirect("login");
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
//        processRequest(request, response,request.getParameter("at"));
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        if (u == null) {
            response.sendRedirect("login");
            return;
        }
        UserDAO ud = new UserDAO();
        u = ud.getUser(u.getId());

        if (request.getParameter("name") != null
                && request.getParameter("pass") != null
                && request.getParameter("email") != null
                && request.getParameter("qn") != null
                && request.getParameter("MMHHdd") != null
                && request.getParameter("at") != null) {
            u.setName(request.getParameter("name"));
            u.setEmail(request.getParameter("email"));
            u.setPass(request.getParameter("pass"));
            if (request.getParameter("dob") != null) {
                u.setDob((Date) request.getAttribute("dob"));
            } else {
                u.setDob(null);
            }

            String qnGet = request.getParameter("qn");
            String qnMul = request.getParameter("MMHHdd");
            int qn = Integer.parseInt(qnGet);

            if (!(qnGet == null || qnGet.length() == 0)) {
                u.getSetting().setQ_Notice(qn);
                u.getSetting().setQN_period(qnMul);
            }
            if (request.getParameter("at") != null) {
                if (request.getParameter("at").length() != 0) {
                    if (request.getParameter("at").split(":").length > 2) {
                        u.getSetting().setA_Time1(Time.valueOf(request.getParameter("at")));
                    } else {
                        u.getSetting().setA_Time1(Time.valueOf(request.getParameter("at") + ":00"));
                    }
                }
            }
            if (request.getParameter("at2") != null) {
                if (request.getParameter("at2").length() != 0) {
                    if (request.getParameter("at2").split(":").length > 2) {
                        u.getSetting().setA_Time2(Time.valueOf(request.getParameter("at2")));
                    } else {
                        u.getSetting().setA_Time2(Time.valueOf(request.getParameter("at2") + ":00"));
                    }
                } else {
                    u.getSetting().setA_Time2(null);
                }
            } else {
                u.getSetting().setA_Time2(null);
            }
            if (request.getParameter("at3") != null) {
                if (request.getParameter("at3").length() != 0) {
                    if (request.getParameter("at3").split(":").length > 2) {
                        u.getSetting().setA_Time3(Time.valueOf(request.getParameter("at3")));
                    } else {
                        u.getSetting().setA_Time3(Time.valueOf(request.getParameter("at3") + ":00"));
                    }
                } else {
                    u.getSetting().setA_Time3(null);
                }
            } else {
                u.getSetting().setA_Time3(null);
            }

            ud.updateUser(u);
            session.setAttribute("user", u);
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    c.setValue(u.getName());
                }
                if (c.getName().equals("password")) {
                    c.setValue(u.getPass());
                }
            }
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet updateSetting</title>");
                out.println("</head>");
                out.println("<body onload=\"setTimeout(function(){document.location = 'main'}, 3000)\">");
                out.println("<h1>Update successful!</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        } else {
            processRequest(request, response, "Required field(s) not filled!");
        }
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
