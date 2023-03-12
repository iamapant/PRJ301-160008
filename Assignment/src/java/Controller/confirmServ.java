/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DB.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author iamap
 */
public class confirmServ extends HttpServlet {

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
            out.println("<title>Servlet confirmServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet confirmServ at " + request.getContextPath() + "</h1>");
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


            UserDAO ud = new UserDAO();
            HttpSession session = request.getSession();
            User u = (User)session.getAttribute("createUser");
            
                ud.insertUser(u);
            
            display(true,request,response);
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

    private void display(boolean b, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add</title>");            
            out.println("</head>");
            out.println("<body onload=\"setTimeout(function(){document.location = 'login.jsp'}, 3000)\">");
            if(b)out.println("<p>User added.</p>");
            else out.println("<p>User not added.</p>");
            out.println("</body>");
            if(b)out.println("<meta http-equiv=\"refresh\" content=\"3;url=login\">");
            else out.println("<meta http-equiv=\"refresh\" content=\"3;url=create\">");
            out.println("</html>");
            HttpSession session = request.getSession();
            session.removeAttribute("createUser");
            
//            if(b) request.getRequestDispatcher("login").forward(request, response);
//            else request.getRequestDispatcher("index.html").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(confirmServ.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        catch (ServletException ex) {
//            Logger.getLogger(confirmServ.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
