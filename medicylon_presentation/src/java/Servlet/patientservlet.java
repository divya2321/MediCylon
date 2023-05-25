/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.Patient.PatientClientProcedure;
import Server.Patient.PatientModel;
import Server.Staff.StaffClientProcedure;
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
public class patientservlet extends HttpServlet {

    
     PatientClientProcedure patientClientProcedure;
     StaffClientProcedure staffClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        staffClientProcedure = StaffClientProcedure.getStaffClientProcedure();
        patientClientProcedure = PatientClientProcedure.getPatientClientProcedure();
    }
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet patientservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet patientservlet at " + request.getContextPath() + "</h1>");
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
        
         String updateNic = request.getParameter("updateNic");
        if (updateNic.equals("")) {
            
            PatientModel patientModel = new PatientModel();
            
            patientModel.setNic(request.getParameter("nic").trim());
            patientModel.setFname(request.getParameter("fname").trim());

            String mname = request.getParameter("mname");
            if (mname != null) {
                patientModel.setMname(mname.trim());
            } else {
                patientModel.setMname(mname);
            }
            patientModel.setLname(request.getParameter("lname").trim());
            patientModel.setContact1(request.getParameter("contact1").trim());

            String contact2 = request.getParameter("contact2");
            if (contact2 != null) {
                patientModel.setContact2(contact2.trim());
            } else {
                patientModel.setContact2(contact2);
            }

            patientModel.setEmail(request.getParameter("email").trim());
            
            if(patientClientProcedure.insertPatient(patientModel).getStatus() == 202){
                response.sendRedirect("lab_receptionist/receptionist_patient.jsp");
            }
        } else {
            doPut(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PatientModel patientModel = new PatientModel();
            
            patientModel.setNic(req.getParameter("nic").trim());
            patientModel.setFname(req.getParameter("fname").trim());

            String mname = req.getParameter("mname");
            if (mname != null) {
                patientModel.setMname(mname.trim());
            } else {
                patientModel.setMname(mname);
            }
            patientModel.setLname(req.getParameter("lname").trim());
            patientModel.setContact1(req.getParameter("contact1").trim());

            String contact2 = req.getParameter("contact2");
            if (contact2 != null) {
                patientModel.setContact2(contact2.trim());
            } else {
                patientModel.setContact2(contact2);
            }

            patientModel.setEmail(req.getParameter("email").trim());
            
            patientModel.setOldNic(req.getParameter("updateNic"));
            
            if(patientClientProcedure.updatePatient(patientModel).getStatus() == 202){
                resp.sendRedirect("lab_receptionist/receptionist_patient.jsp");
            }
    }
    
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
