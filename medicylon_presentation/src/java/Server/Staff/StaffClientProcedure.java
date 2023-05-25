package Server.Staff;

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


public class StaffClientProcedure {
    
    
       private static StaffClientProcedure instanceStaff = null;
    
    private StaffClientProcedure(){}
    
    public static synchronized StaffClientProcedure getStaffClientProcedure(){
        if (instanceStaff==null) {
            instanceStaff = new StaffClientProcedure();
        }
        return instanceStaff;
    }
    
    
    public Response insertStaff(StaffModel staffModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/staff/insertstaff");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(staffModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    public Response updateStaff(StaffModel staffModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/staff/updatestaff");
        Response response = webTarget.request().put(Entity.entity(staffModel, MediaType.APPLICATION_JSON));
        return response;
    }
        
    public static  ArrayList<StaffModel> loadStaff(){
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<StaffModel>> genericType = new GenericType<ArrayList<StaffModel>>(){};
        ArrayList<StaffModel> returnStaffList = client.target(getURI())
                .path("business/staff/loadstaff").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }
    
     public static  ArrayList<StaffModel> loadStaffIdNic(){
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<StaffModel>> genericType = new GenericType<ArrayList<StaffModel>>(){};
        ArrayList<StaffModel> returnStaffList = client.target(getURI())
                .path("business/staff/loadstaffidnic").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }
     
      public static  ArrayList<StaffModel> loadTechnicianIdName(){
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<StaffModel>> genericType = new GenericType<ArrayList<StaffModel>>(){};
        ArrayList<StaffModel> returnStaffList = client.target(getURI())
                .path("business/staff/loadtechnicianidname").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }
    
       public boolean isNicHave(String nic) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("business/staff/nichave").path(nic).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }
    
    
    private static URI getURI(){
        return UriBuilder.fromUri("http://localhost:8080/medicylon_business").build();
    }
    
    public static void main(String[] args) {
        System.out.println(new StaffClientProcedure().loadStaffIdNic());
    }
}