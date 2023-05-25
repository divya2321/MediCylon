/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Server.Appointment.AppointmentClientProcedure;
import Server.Appointment.AppointmentModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author divsi
 */
public class appointmentservlet extends HttpServlet {

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
            out.println("<title>Servlet appointment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet appointment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        String appointmentCode = request.getParameter("code");
        if (appointmentCode != null) {

            AppointmentModel appointmentModel = new AppointmentModel();
            appointmentModel.setAppointmentCode(appointmentCode);

            ServletOutputStream sos = response.getOutputStream();

            Response r = appointmentClientProcedure.loadReport(appointmentModel);
            AppointmentModel model = r.readEntity(AppointmentModel.class);
            sos.write(model.getReport());

            sos.flush();
            sos.close();        
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
