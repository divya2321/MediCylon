/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.Appointment.AppointmentClientProcedure;
import Server.Appointment.AppointmentModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author divsi
 */

@WebServlet("/uploadreportservlet")
@MultipartConfig() 
public class uploadreportservlet extends HttpServlet {
    
        AppointmentClientProcedure appointmentClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentClientProcedure = AppointmentClientProcedure.getAppointmentClientProcedure();
    }


 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet uploadreportservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet uploadreportservlet at " + request.getContextPath() + "</h1>");
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
       
        Part reportPart = request.getPart("report");
        String appointmentCode = request.getParameter("appointmentCode");
        String patientNic = request.getParameter("patientNic");
        
//        out.println(reportPart);
//        out.println(appointmentCode);
        
        AppointmentModel appointmentModel = new AppointmentModel();
        
        InputStream fi =  reportPart.getInputStream();
        
           ByteArrayOutputStream os = new ByteArrayOutputStream(); 
    byte[] buffer = new byte[0xFFFF];
            try {
                for (int len = fi.read(buffer); len != -1; len = fi.read(buffer)) {
                    
                    os.write(buffer, 0, len);
                }       
            
            } catch (IOException ex) {
                Logger.getLogger(uploadreportservlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//             model.setReport( );
        appointmentModel.setReport(os.toByteArray());
        appointmentModel.setAppointmentCode(appointmentCode);
        appointmentModel.setPatientNic(patientNic);
        
         HttpSession sess = request.getSession(false);
            
            appointmentModel.setUsername((String) sess.getAttribute("username"));
        
         if (appointmentClientProcedure.uploadAppointment(appointmentModel).getStatus() == 202) {
                response.sendRedirect("lab_technician/technician_dashboard.jsp");
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
