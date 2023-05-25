/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.Appointment.AppointmentClientProcedure;
import Server.Appointment.AppointmentModel;
import Server.Patient.PatientClientProcedure;
import Server.Test.TestClientProcedure;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author divsi
 */
public class appointmentservlet extends HttpServlet {

    AppointmentClientProcedure appointmentClientProcedure;
    TestClientProcedure testClientProcedure;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentClientProcedure = AppointmentClientProcedure.getAppointmentClientProcedure();
        testClientProcedure = TestClientProcedure.getUserClientProcedure();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet appointmentservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet appointmentservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        if (name.equalsIgnoreCase("availibility")) {

            String test = request.getParameter("test");
            String date = request.getParameter("date");

            AppointmentModel appointmentModel = new AppointmentModel();
            appointmentModel.setTest(test);
            appointmentModel.setAppointmentDate(date);

            Response r = appointmentClientProcedure.canPlaceAppointment(appointmentModel);

            boolean b = r.readEntity(Boolean.class);

            if (b) {
                out.println("ok");
            } else {
                out.println("Maximum appointment count exceeded!");
            }

        } else {
            String test = request.getParameter("test");

            String price = testClientProcedure.getTestPriceByTest(test);

            out.println(price);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appointmentCode = request.getParameter("appointmentCode");
        String name = request.getParameter("name");

        if (name.equals("addupdate")) {
        if (appointmentCode.equals("")) {

            AppointmentModel appointmentModel = new AppointmentModel();

            appointmentModel.setPatientNic(request.getParameter("nic"));
            appointmentModel.setTest(request.getParameter("test"));
            appointmentModel.setDoctor(request.getParameter("doctor"));
            appointmentModel.setAppointmentDate(request.getParameter("appointmentdate"));
            appointmentModel.setAppointmentTime(request.getParameter("appointmenttime"));
            appointmentModel.setAmount(request.getParameter("amount"));
            appointmentModel.setPaymentType(request.getParameter("paymenttype"));
            
            HttpSession sess = request.getSession(false);
            
            appointmentModel.setUsername((String) sess.getAttribute("username"));
            
            appointmentModel.setStatus("appointed");

            if (appointmentClientProcedure.insertAppointment(appointmentModel).getStatus() == 202) {
                response.sendRedirect("lab_receptionist/receptionist_appointment.jsp");
            }

        } else {
            doPut(request, response);
        }
    }else{
           doPut(request, response);  
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AppointmentModel appointmentModel = new AppointmentModel();

        String appointmentCode = req.getParameter("appointmentCode");
        String name = req.getParameter("name");
        
        if (name.equals("cancel")) {

                  appointmentModel.setAppointmentCode(appointmentCode);
            appointmentModel.setStatus("canceled");
            
//             PrintWriter out = resp.getWriter();
//             out.println(cancelAppointmentCode);

            if (appointmentClientProcedure.cancelAppointment(appointmentModel).getStatus() == 202) {
                resp.sendRedirect("lab_receptionist/receptionist_appointment.jsp");
            }


        } else {
               appointmentModel.setDoctor(req.getParameter("doctor"));
            appointmentModel.setAppointmentDate(req.getParameter("appointmentdate"));
            appointmentModel.setAppointmentTime(req.getParameter("appointmenttime"));
            appointmentModel.setAppointmentCode(appointmentCode);
            appointmentModel.setTest(req.getParameter("updateTest"));

            if (appointmentClientProcedure.updateAppointment(appointmentModel).getStatus() == 202) {
                resp.sendRedirect("lab_receptionist/receptionist_appointment.jsp");
            }
            
      
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
