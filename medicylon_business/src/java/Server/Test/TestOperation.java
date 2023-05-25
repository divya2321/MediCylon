package Server.Test;

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
@Path("/test")
public class TestOperation {

    @POST
    @Path("/insertcategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTestCategory(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/test/insertcategory");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/updatecategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateTestCategory(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/test/updatecategory");
        Response response = webTarget.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
     @POST
    @Path("/inserttest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTest(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/test/inserttest");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/updatetest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateTest(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/test/updatetest");
        Response response = webTarget.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("/loadcategory")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<TestModel> loadTestCategory() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<TestModel>> genericType = new GenericType<ArrayList<TestModel>>() {
        };
        ArrayList<TestModel> returnUserList = client.target(getURI())
                .path("data/test/loadcategory").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnUserList;
    }
    
     @GET
    @Path("/loadtest")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<TestModel> loadTest() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<TestModel>> genericType = new GenericType<ArrayList<TestModel>>() {
        };
        ArrayList<TestModel> returnUserList = client.target(getURI())
                .path("data/test/loadtest").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnUserList;
    }

    @GET
    @Path("/categoryhave/{category}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestCategoryHave(@PathParam("category") String category) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("data/test/categoryhave").path(category).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }
    
      @GET
    @Path("/testhave/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestHave(@PathParam("test") String test) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("data/test/testhave").path(test).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }
    
    @GET
    @Path("/category/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCategoryByTest(@PathParam("test") String test) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        String returnboolean = client.target(getURI())
                .path("data/test/category").path(test).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        return returnboolean;
    }
    
       @GET
    @Path("/testid/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestIdByTest(@PathParam("testid") String testId) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        String returnboolean = client.target(getURI())
                .path("data/test/testid").path(testId).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        return returnboolean;
    }
    
         @GET
    @Path("/testprice/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestPriceByTest(@PathParam("test") String test) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        String returnboolean = client.target(getURI())
                .path("data/test/testprice").path(test).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        return returnboolean;
    }


    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost:8080/medicylon_data").build();
    }

}
