/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.User.UserClientProcedure;
import Server.User.UserModel;
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

    UserClientProcedure userClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userClientProcedure = UserClientProcedure.getUserClientProcedure();
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
        String username = request.getParameter("username").trim();
        String password = Encription.Encript.encript(request.getParameter("password").trim());

        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);
        Response r = userClientProcedure.checkUsernamePassword(userModel);
        if (r.getStatus() == 202) {

            UserModel logUserModel = r.readEntity(UserModel.class);

            HttpSession session = request.getSession();
            session.setAttribute("role", logUserModel.getJob());
            session.setAttribute("name", logUserModel.getStaffName());
            session.setAttribute("username", logUserModel.getUsername());
            session.setMaxInactiveInterval(60 * 60);

            Cookie userName = new Cookie("username", username);
            userName.setMaxAge(60 * 60);
            response.addCookie(userName);

            if (logUserModel.getJob().equalsIgnoreCase("admin")) {
                response.sendRedirect("lab_admin/admin_dashboard.jsp");
            } else if (logUserModel.getJob().equalsIgnoreCase("receptionist")) {
                response.sendRedirect("lab_receptionist/receptionist_dashboard.jsp");
            } else if (logUserModel.getJob().equalsIgnoreCase("technician")) {
                response.sendRedirect("lab_technician/technician_dashboard.jsp");
            }
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect');");
            out.println("location='log_in_form.jsp';");
            out.println("</script>");
            
            
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
