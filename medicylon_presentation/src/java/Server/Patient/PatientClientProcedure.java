package Server.Patient;

import java.net.URI;
import java.util.ArrayList;
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


public class PatientClientProcedure {
    
    
       private static PatientClientProcedure instancePatient = null;
    
    private PatientClientProcedure(){}
    
    public static synchronized PatientClientProcedure getPatientClientProcedure(){
        if (instancePatient==null) {
            instancePatient = new PatientClientProcedure();
        }
        return instancePatient;
    }
    
    
    public Response insertPatient(PatientModel patientModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/patient/insertpatient");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(patientModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    public Response updatePatient(PatientModel patientModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/patient/updatepatient");
        Response response = webTarget.request().put(Entity.entity(patientModel, MediaType.APPLICATION_JSON));
        return response;
    }
        
    public static  ArrayList<PatientModel> loadPatient(){
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<PatientModel>> genericType = new GenericType<ArrayList<PatientModel>>(){};
        ArrayList<PatientModel> returnPatientList = client.target(getURI())
                .path("business/patient/loadpatient").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnPatientList;
    }
    
    
    private static URI getURI(){
        return UriBuilder.fromUri("http://localhost:8080/medicylon_business").build();
    }
    
    public static void main(String[] args) {
//        System.out.println(new PatientClientProcedure().loadStaffIdNic());
    }
}