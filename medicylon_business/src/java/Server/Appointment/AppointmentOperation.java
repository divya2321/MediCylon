package Server.Appointment;

import Server.Patient.PatientOperation;
import Server.Test.TestOperation;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import sun.misc.IOUtils;

/**
 *
 * @author divsi
 */
@Path("/appointment")
public class AppointmentOperation {

    @POST
    @Path("/insertappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertAppointment(AppointmentModel appointmentModel) {

        PatientOperation patientOperation = new PatientOperation();

        int patientId = patientOperation.getPatientId(appointmentModel.getPatientNic());
        appointmentModel.setPatientId(String.valueOf(patientId));

        TestOperation testOperation = new TestOperation();

        String testCategory = testOperation.getCategoryByTest(appointmentModel.getTest());
        String testId = testOperation.getTestIdByTest(appointmentModel.getTest());

        appointmentModel.setTestCategory(testCategory);
        appointmentModel.setTestId(testId);

        Response r = getAppointentNo(appointmentModel);
        int no = r.readEntity(Integer.class);
        appointmentModel.setAppointmentNo(String.valueOf(no));

        String patientEmail = patientOperation.getPatientEmail(appointmentModel.getPatientNic());

        if (patientEmail != null) {

            String subject = "Medicylon lab appointment";
            String message = "<p>Test : <strong>" + appointmentModel.getTest() + "</strong> </p>"
                    + " <p>Appointment date : <strong>" + appointmentModel.getAppointmentDate() + "</strong> </p>"
                    + " <p>Appointment time : <strong>" + appointmentModel.getAppointmentTime() + "</strong> </p>"
                    + " <p>Appointment no : <strong>" + appointmentModel.getAppointmentNo() + "</strong> </p>"
                    + " <p>Payment Type : <strong>" + appointmentModel.getPaymentType() + "</strong> </p>"
                    + " <p>Amount : <strong>" + appointmentModel.getAmount() + "</strong> </p>"
                    + "<p>Thank you!</p>";

            JavaMailProvider.MailProvider.sendEmail(patientEmail, subject, message);

        }

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/insertappointment");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public Response getAppointentNo(AppointmentModel appointmentModel) {

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/getappointmentno");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/updateappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateAppointment(AppointmentModel appointmentModel) {

        TestOperation testOperation = new TestOperation();

        String testCategory = testOperation.getCategoryByTest(appointmentModel.getTest());

        appointmentModel.setTestCategory(testCategory);

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/updateappointment");
        Response response = webTarget.request().put(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @POST
    @Path("/uploadappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadAppointment(AppointmentModel appointmentModel) {

        PatientOperation patientOperation = new PatientOperation();

        String patientEmail = patientOperation.getPatientEmail(appointmentModel.getPatientNic());

        if (patientEmail != null) {

            String subject = "Medicylon lab appointment";
            String message = "<p>Your test (" + appointmentModel.getAppointmentCode() + ") report has been uploaded! Please check via your account!</p>"
                    + "<p>Thank you!</p>";

            JavaMailProvider.MailProvider.sendEmail(patientEmail, subject, message);

        }

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/uploadappointment");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/cancelappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response cancelAppointment(AppointmentModel appointmentModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/cancelappointment");
        Response response = webTarget.request().put(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("/loadappointment")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AppointmentModel> loadAppointment() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<AppointmentModel>> genericType = new GenericType<ArrayList<AppointmentModel>>() {
        };
        ArrayList<AppointmentModel> returnPatientList = client.target(getURI())
                .path("data/appointment/loadappointment").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnPatientList;
    }

    @POST
    @Path("/canplaceappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response canPlaceAppointment(AppointmentModel appointmentModel) {

        TestOperation testOperation = new TestOperation();
        String testCategory = testOperation.getCategoryByTest(appointmentModel.getTest());

        appointmentModel.setTestCategory(testCategory);

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/canplaceappointment");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @POST
    @Path("/loadreport")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadReport(AppointmentModel appointmentModel) {

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/appointment/loadreport");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost:8080/medicylon_data").build();
    }

    public static void main(String[] args) {

//        AppointmentOperation appointmentOperation = new AppointmentOperation();
        AppointmentModel model = new AppointmentModel();
//      
//      model.setAppointmentCode("10");
//      model.setUsername("nipz2123");
//        try {
//           
//            
//          FileInputStream fi =  new FileInputStream(new File("C:\\Users\\divsi\\OneDrive\\Desktop\\hr1.pdf"));
//            
//            ByteArrayOutputStream os = new ByteArrayOutputStream(); 
//    byte[] buffer = new byte[0xFFFF];
//            try {
//                for (int len = fi.read(buffer); len != -1; len = fi.read(buffer)) {
//                    
//                    os.write(buffer, 0, len);
//                }       
//            
//            } catch (IOException ex) {
//                Logger.getLogger(AppointmentOperation.class.getName()).log(Level.SEVERE, null, ex);
//            }
//             model.setReport( os.toByteArray());
//    
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(AppointmentOperation.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        System.out.println(new AppointmentOperation().uploadAppointment(model));
//  AppointmentModel model1 = new AppointmentModel();
        model.setAppointmentCode("8");
        Response r = new AppointmentOperation().loadReport(model);
        PatientOperation patientOperation = new PatientOperation();
        System.out.println(patientOperation.getPatientEmail("998429450V"));

    }

}
