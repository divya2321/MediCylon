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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/**
 *
 * @author divsi
 */
public class changepasswordservlet extends HttpServlet {

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
            out.println("<title>Servlet changepasswordservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changepasswordservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        String username = (String) session.getAttribute("username");
        String password = Encription.Encript.encript(request.getParameter("pass"));

        if (username != null) {

            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setPassword(password);
            Response r = userClientProcedure.checkUsernamePassword(userModel);
            if (r.getStatus() == 202) {
                
                     out.print("");
            }else{
                   out.print("Wrong password!");  
            }
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPut(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
          HttpSession session = req.getSession(false);

        String username = (String) session.getAttribute("username");
        
        if(username != null){
        UserModel userModel = new UserModel();
        userModel.setPassword(Encription.Encript.encript(req.getParameter("newpassword")));
        userModel.setUsername(username);
        
       Response r =  userClientProcedure.changePassword(userModel);
       if(r.getStatus() == 202){
           resp.sendRedirect("logoutservlet");
       }
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
