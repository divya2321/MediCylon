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
import javax.print.DocFlavor;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author divsi
 */
public class userservlet extends HttpServlet {

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
            out.println("<title>Servlet userservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet userservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        if (userClientProcedure.isUsernameHave(username)) {
            out.println("This username alrady have");
        } else {
            out.println("");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        if (name.equals("add")) {
            UserModel userModel = new UserModel();
            
            String staff = request.getParameter("staff");
            userModel.setStaffId(staff.split(" - ")[0]);
            userModel.setUsername(request.getParameter("username").trim());
            userModel.setPassword(Encription.Encript.encript(request.getParameter("password").trim()));
            userModel.setStatus("active");

            if (userClientProcedure.insertUser(userModel).getStatus() == 202) {
                response.sendRedirect("lab_admin/admin_user.jsp");
            }
        } else {
            doPut(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         UserModel userModel = new UserModel();
           String staffId = req.getParameter("updatestaffid");

            userModel.setStaffId(staffId);
            userModel.setStatus(req.getParameter("status"));
            if (userClientProcedure.updateUser(userModel).getStatus() == 202) {
                resp.sendRedirect("lab_admin/admin_user.jsp");
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
