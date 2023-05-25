package Server.User;

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


public class UserClientProcedure {
    
    
       private static UserClientProcedure instanceUser = null;
    
    private UserClientProcedure(){}
    
    public static synchronized UserClientProcedure getUserClientProcedure(){
        if (instanceUser==null) {
            instanceUser = new UserClientProcedure();
        }
        return instanceUser;
    }
    
    
    public Response insertUser(UserModel userModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/user/insertuser");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    public Response updateUser(UserModel userModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/user/updateuser");
        Response response = webTarget.request().put(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }
        
    public static  ArrayList<UserModel> loadUser(){
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<UserModel>> genericType = new GenericType<ArrayList<UserModel>>(){};
        ArrayList<UserModel> returnUserList = client.target(getURI())
                .path("business/user/loaduser").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnUserList;
    }
    
    public boolean isUsernameHave(String username) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("business/user/usernamehave").path(username).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }

      public Response checkUsernamePassword(UserModel userModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/user/checkusernamepass");
          Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }
      
        public Response changePassword(UserModel userModel){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/user/changepassword");
        Response response = webTarget.request().put(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    private static URI getURI(){
        return UriBuilder.fromUri("http://localhost:8080/medicylon_business").build();
    }
    
    public static void main(String[] args) {
        UserClientProcedure u = new UserClientProcedure();
        
        UserModel um = new UserModel();;
        
        um.setUsername("divz2123");
        um.setPassword("IoX/EeaCah541r4v93Od+g==");
        
        System.out.println(u.checkUsernamePassword(um));
    }
 
}