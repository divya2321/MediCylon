package Server.PatientSystemLogin;

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


public class PatientLoginClientProcedure {
    
    
       private static PatientLoginClientProcedure instancePatientLogin = null;
    
    private PatientLoginClientProcedure(){}
    
    public static synchronized PatientLoginClientProcedure getPatientLoginClientProcedure(){
        if (instancePatientLogin==null) {
            instancePatientLogin = new PatientLoginClientProcedure();
        }
        return instancePatientLogin;
    }
    
    
    public Response checkIdPassword(PatientLoginModel patientLoginModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/patientlogin/login");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(patientLoginModel, MediaType.APPLICATION_JSON));
        return response;
    }
 
    
    private static URI getURI(){
        return UriBuilder.fromUri("http://localhost:8080/medicylon_business").build();
    }
 
    
    public static void main(String[] args) {
        PatientLoginModel p = new PatientLoginModel();
        p.setId("Ml4Fv4Ex");
        p.setPassword("ZFls7U+Ji5t8D1UwCz6LXQ==");
        
        PatientLoginClientProcedure pl = new PatientLoginClientProcedure();
        Response r = pl.checkIdPassword(p);
        
        System.out.println(r.readEntity(String.class).isEmpty());
    }
}