package Server.Patient;

import java.net.URI;
import java.util.ArrayList;
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

/**
 *
 * @author divsi
 */
@Path("/patient")
public class PatientOperation {

    @POST
    @Path("/insertpatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPatient(PatientModel patientModel) {

        String id = RandomValueGenerator.RandomValueGenerator.generateRandomId();
        String password = RandomValueGenerator.RandomValueGenerator.generateRandomPassword();

        patientModel.setId(id);
        patientModel.setPassword(Encription.Encript.encript(password));
        
        PatientOperation patientOperation = new PatientOperation();
        
        String patientEmail = patientModel.getEmail();
        
        if(patientEmail != null){
        
        String subject = "Medicylon lab appointment";
        String message = "<p>Please use following credentials to login into your account</p>"
                         +"<p>Userid: <strong>"+id+"</strong> </p>"
                         +"<p>Password: <strong>"+password+"</strong> </p>"
                         +"<p>Thank you!</p>";

        JavaMailProvider.MailProvider.sendEmail(patientEmail, subject, message);
        
        }

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/patient/insertpatient");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(patientModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/updatepatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response updatePatient(PatientModel patientModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/patient/updatepatient");
        Response response = webTarget.request().put(Entity.entity(patientModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("/loadpatient")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<PatientModel> loadPatient() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<PatientModel>> genericType = new GenericType<ArrayList<PatientModel>>() {
        };
        ArrayList<PatientModel> returnPatientList = client.target(getURI())
                .path("data/patient/loadpatient").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnPatientList;
    }

    @GET
    @Path("patientid/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getPatientId(@PathParam("nic") String nic) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        int id = client.target(getURI())
                .path("data/patient/patientid").path(nic).request().accept(MediaType.TEXT_PLAIN).get(Integer.class);
        return id;
    }
    
      @GET
    @Path("patientemail/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPatientEmail(@PathParam("nic") String nic) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        String email = client.target(getURI())
                .path("data/patient/patientemail").path(nic).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        return email;
    }


    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost:8080/medicylon_data").build();
    }

    public static void main(String[] args) {

//        StaffModel staffModel = new StaffModel();
//        staffModel.setNic("985623560V");
//        staffModel.setFname("Anura");
//        staffModel.setLname("Kumara");
//        staffModel.setContact1("0775623600");
//        staffModel.setEmail("anura.kumara@gmail.com");
//        staffModel.setNo("541/2");
//        staffModel.setStreet1("Kadahapola");
//        staffModel.setCity("Mahara");
//        staffModel.setJob("technician");
//        staffModel.setStatus("active");
////        staffModel.setStaffId(2);
//
        System.out.println(new PatientOperation().getPatientEmail("998429450V"));
    }

}
