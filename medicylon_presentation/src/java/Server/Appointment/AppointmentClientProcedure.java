package Server.Appointment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author divsi
 */


public class AppointmentClientProcedure {
    
    
       private static AppointmentClientProcedure instanceAppointment = null;
    
    private AppointmentClientProcedure(){}
    
    public static synchronized AppointmentClientProcedure getAppointmentClientProcedure(){
        if (instanceAppointment==null) {
            instanceAppointment = new AppointmentClientProcedure();
        }
        return instanceAppointment;
    }
    
    
    public Response insertAppointment(AppointmentModel appointmentModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/appointment/insertappointment");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    public Response updateAppointment(AppointmentModel appointmentModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/appointment/updateappointment");
        Response response = webTarget.request().put(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
      public Response uploadAppointment(AppointmentModel appointmentModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/appointment/uploadappointment");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
      public Response cancelAppointment(AppointmentModel appointmentModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/appointment/cancelappointment");
        Response response = webTarget.request().put(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }
        
    public static  ArrayList<AppointmentModel> loadAppointment(){
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<AppointmentModel>> genericType = new GenericType<ArrayList<AppointmentModel>>(){};
        ArrayList<AppointmentModel> returnStaffList = client.target(getURI())
                .path("business/appointment/loadappointment").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }
    
//        public static  ArrayList<AppointmentModel> loadReportAppointment(){
//        Client client = ClientBuilder.newClient(new ClientConfig());
//        GenericType<ArrayList<AppointmentModel>> genericType = new GenericType<ArrayList<AppointmentModel>>(){};
//        ArrayList<AppointmentModel> returnStaffList = client.target(getURI())
//                .path("business/appointment/loadreportappointment").request().accept(MediaType.APPLICATION_JSON).get(genericType);
//        return returnStaffList;
//    }
//    
    public Response canPlaceAppointment(AppointmentModel appointmentModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/appointment/canplaceappointment");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    
    
    private static URI getURI(){
        return UriBuilder.fromUri("http://localhost:8080/medicylon_business").build();
    }
    
    public static void main(String[] args) {
               AppointmentModel model = new AppointmentModel();
      
//      model.setAppointmentCode("5");
//      model.setUsername("nipz2123");
//        try {
//            model.setReport(new FileInputStream(new File("C:\\Users\\divsi\\OneDrive\\Desktop\\hr1.pdf")));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AppointmentClientProcedure.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        System.out.println(new AppointmentClientProcedure().loadReportAppointment());
    }
}