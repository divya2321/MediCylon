package Server.User;

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
@Path("/user")
public class UserOperation {

    @POST
    @Path("/insertuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(UserModel userModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/user/insertuser");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/updateuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateUser(UserModel userModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/user/updateuser");
        Response response = webTarget.request().put(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("/loaduser")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserModel> loadUser() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<UserModel>> genericType = new GenericType<ArrayList<UserModel>>() {
        };
        ArrayList<UserModel> returnUserList = client.target(getURI())
                .path("data/user/loaduser").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnUserList;
    }

    @GET
    @Path("/usernamehave/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isUsernameHave(@PathParam("username") String username) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("data/user/usernamehave").path(username).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }

    @POST
    @Path("/checkusernamepass")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUsernamePassword(UserModel userModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/user/checkusernamepass");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;

    }

    @PUT
    @Path("/changepassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response changePassword(UserModel userModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/user/changepassword");
        Response response = webTarget.request().put(Entity.entity(userModel, MediaType.APPLICATION_JSON));
        return response;
    }

    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost:8080/medicylon_data").build();
    }

    public static void main(String[] args) {
        UserOperation uo = new UserOperation();
        UserModel um = new UserModel();;

        um.setUsername("nipz2123");
        um.setPassword("Nipun2123");

        System.out.println(uo.checkUsernamePassword(um));
    }

}
