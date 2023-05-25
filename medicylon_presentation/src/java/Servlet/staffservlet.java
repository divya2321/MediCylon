/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.Staff.StaffClientProcedure;
import Server.Staff.StaffModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author divsi
 */
public class staffservlet extends HttpServlet {

    StaffClientProcedure staffClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        staffClientProcedure = StaffClientProcedure.getStaffClientProcedure();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet staffservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet staffservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
                
            String oldNic = request.getParameter("oldnic").trim();
            String nic = request.getParameter("nic").trim();

            if (oldNic.equals("")) {

                if (staffClientProcedure.isNicHave(nic)) {
                    out.println("This nic already have");
                } else {
                    out.println("");
                }

            } else {
                if (staffClientProcedure.isNicHave(nic) && !nic.equalsIgnoreCase(oldNic)) {
                    out.println("This nic already have");
                } else {
                    out.println("");
                }

            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String updateStaffId = request.getParameter("updatestaffid");
        if (updateStaffId.equals("")) {
            
            StaffModel staffModel = new StaffModel();
            
            staffModel.setNic(request.getParameter("nic").trim());
            staffModel.setFname(request.getParameter("fname").trim());

            String mname = request.getParameter("mname");
            if (mname != null) {
                staffModel.setMname(mname.trim());
            } else {
                staffModel.setMname(mname);
            }
            staffModel.setLname(request.getParameter("lname").trim());
            staffModel.setContact1(request.getParameter("contact1").trim());

            String contact2 = request.getParameter("contact2");
            if (contact2 != null) {
                staffModel.setContact2(contact2.trim());
            } else {
                staffModel.setContact2(contact2);
            }

            staffModel.setEmail(request.getParameter("email").trim());
            staffModel.setJob(request.getParameter("job").trim());
            staffModel.setNo(request.getParameter("no").trim());
            staffModel.setStreet1(request.getParameter("street1").trim());

            String street2 = request.getParameter("street2");
            if (street2 != null) {
                staffModel.setStreet2(street2.trim());
            } else {
                staffModel.setStreet2(street2);
            }

            staffModel.setCity(request.getParameter("city").trim());
            staffModel.setStatus("active");
            
            if(staffClientProcedure.insertStaff(staffModel).getStatus() == 202){
                response.sendRedirect("lab_admin/admin_staff.jsp");
            }
        } else {
            doPut(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          StaffModel staffModel = new StaffModel();
            
            staffModel.setNic(req.getParameter("nic").trim());
            staffModel.setFname(req.getParameter("fname").trim());

            String mname = req.getParameter("mname");
            if (mname != null) {
                staffModel.setMname(mname.trim());
            } else {
                staffModel.setMname(mname);
            }
            staffModel.setLname(req.getParameter("lname").trim());
            staffModel.setContact1(req.getParameter("contact1").trim());

            String contact2 = req.getParameter("contact2");
            if (contact2 != null) {
                staffModel.setContact2(contact2.trim());
            } else {
                staffModel.setContact2(contact2);
            }

            staffModel.setEmail(req.getParameter("email").trim());
            staffModel.setJob(req.getParameter("job").trim());
            staffModel.setNo(req.getParameter("no").trim());
            staffModel.setStreet1(req.getParameter("street1").trim());

            String street2 = req.getParameter("street2");
            if (street2 != null) {
                staffModel.setStreet2(street2.trim());
            } else {
                staffModel.setStreet2(street2);
            }

            staffModel.setCity(req.getParameter("city").trim());
            staffModel.setStatus(req.getParameter("status"));
            staffModel.setStaffId(Integer.parseInt(req.getParameter("updatestaffid")));
            
            if(staffClientProcedure.updateStaff(staffModel).getStatus() == 202){
                resp.sendRedirect("lab_admin/admin_staff.jsp");
            }
    }
    
    
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
