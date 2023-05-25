package Server.Test;

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
public class TestClientProcedure {

    private static TestClientProcedure instanceTest = null;

    private TestClientProcedure() {
    }

    public static synchronized TestClientProcedure getUserClientProcedure() {
        if (instanceTest == null) {
            instanceTest = new TestClientProcedure();
        }
        return instanceTest;
    }

    public Response insertTestCategory(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/test/insertcategory");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response updateTestCategory(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/test/updatecategory");
        Response response = webTarget.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response insertTest(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/test/inserttest");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response updateTest(TestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("business/test/updatetest");
        Response response = webTarget.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public static ArrayList<TestModel> loadTestCategory() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<TestModel>> genericType = new GenericType<ArrayList<TestModel>>() {
        };
        ArrayList<TestModel> returnCategoryList = client.target(getURI())
                .path("business/test/loadcategory").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnCategoryList;
    }

    public static ArrayList<TestModel> loadTest() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<TestModel>> genericType = new GenericType<ArrayList<TestModel>>() {
        };
        ArrayList<TestModel> returnTestList = client.target(getURI())
                .path("business/test/loadtest").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnTestList;
    }

    public boolean isTestCategoryHave(String category) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("business/test/categoryhave").path(category).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }

    public boolean isTestHave(String test) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("business/test/testhave").path(test).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }
    
       public String getTestPriceByTest(String test) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        String price = client.target(getURI())
                .path("business/test/testprice").path(test).request().accept(MediaType.TEXT_PLAIN).get(String.class);
        return price;
    }

    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost:8080/medicylon_business").build();
    }
    
    
    public static void main(String[] args) {
        TestClientProcedure t = new TestClientProcedure();
        System.out.println(t.getTestPriceByTest("Computational anatomy"));
    }

}
