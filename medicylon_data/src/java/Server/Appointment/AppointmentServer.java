package Server.Appointment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author divsi
 */
@Path("/appointment")
public class AppointmentServer {

    AppointmentFunction appointmentFunction = AppointmentFunction.getAppointmentFunction();

    @POST
    @Path("/insertappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertAppointment(AppointmentModel appointmentModel) {
        boolean inserted = appointmentFunction.insertAppointment(appointmentModel);
        return Response.status(202).entity(inserted).build();
    }

    @PUT
    @Path("/updateappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(AppointmentModel appointmentModel) {
        boolean updated = appointmentFunction.updateAppointment(appointmentModel);
        return Response.status(202).entity(updated).build();
    }

    @POST
    @Path("/uploadappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadAppointment(AppointmentModel appointmentModel) {
        boolean uploaded = appointmentFunction.uploadReport(appointmentModel);
        return Response.status(202).entity(uploaded).build();
    }

    @PUT
    @Path("/cancelappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelAppointment(AppointmentModel appointmentModel) {
        boolean updated = appointmentFunction.cancelAppointment(appointmentModel);
        return Response.status(202).entity(updated).build();
    }

    @GET
    @Path("/loadappointment")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AppointmentModel> loadAppointment() {
        return appointmentFunction.getAllAppointment();
    }
//
//    @GET
//    @Path("/loadreportappointment")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<AppointmentModel> loadReportAppointment() {
//        return appointmentFunction.getAllReportAppointment();
//    }

    @POST
    @Path("/canplaceappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response canPlaceppointment(AppointmentModel appointmentModel) {
        boolean isCan = appointmentFunction.canPlaceAppointment(appointmentModel);
        return Response.status(202).entity(isCan).build();
    }

    @POST
    @Path("/loadreport")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadReport(AppointmentModel appointmentModel) {
        AppointmentModel report = appointmentFunction.loadReport(appointmentModel);
        return Response.status(202).entity(report).build();
    }
    
        @POST
    @Path("/getappointmentno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointentNo(AppointmentModel appointmentModel) {
        int no = appointmentFunction.getAppointmentNo(appointmentModel.getTestCategory(),appointmentModel.getAppointmentDate());
        return Response.status(202).entity(no).build();
    }

}
