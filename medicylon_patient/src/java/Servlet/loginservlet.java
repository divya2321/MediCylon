/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.PatientSystemLogin.PatientLoginClientProcedure;
import Server.PatientSystemLogin.PatientLoginModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/**
 *
 * @author divsi
 */
public class loginservlet extends HttpServlet {
    
        PatientLoginClientProcedure patientLoginClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        patientLoginClientProcedure = PatientLoginClientProcedure.getPatientLoginClientProcedure();
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String userId = request.getParameter("userid").trim();
        String password = Encription.Encript.encript(request.getParameter("password").trim());

        PatientLoginModel patientLoginModel = new PatientLoginModel();
        patientLoginModel.setId(userId);
        patientLoginModel.setPassword(password);
        Response r = patientLoginClientProcedure.checkIdPassword(patientLoginModel);
        
        String patientName = r.readEntity(String.class);
        if (!patientName.isEmpty()) {

            HttpSession session = request.getSession();
            session.setAttribute("patientId", userId);
            session.setAttribute("patientName", patientName);
            session.setMaxInactiveInterval(60 * 60);

            Cookie user = new Cookie("patientId", userId);
            user.setMaxAge(60 * 60);
            response.addCookie(user);

                response.sendRedirect("home.jsp");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect');");
            out.println("location='login.jsp';");
            out.println("</script>");
            
            
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
