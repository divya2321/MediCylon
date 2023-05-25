package Server.Test;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author divsi
 */
@Path("/test")
public class TestServer {

    TestFunction testFunction = TestFunction.getTestFunction();

    @POST
    @Path("/insertcategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTestCategory(TestModel testModel) {
        boolean inserted = testFunction.insertTestCategory(testModel);
        return Response.status(202).entity(inserted).build();
    }

    @PUT
    @Path("/updatecategory")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTestCategory(TestModel testModel) {
        boolean updated = testFunction.updateTestCategory(testModel);
        return Response.status(202).entity(updated).build();
    }

    @POST
    @Path("/inserttest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTest(TestModel testModel) {
        boolean inserted = testFunction.insertTest(testModel);
        return Response.status(202).entity(inserted).build();
    }

    @PUT
    @Path("/updatetest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTest(TestModel testModel) {
        boolean updated = testFunction.updateTest(testModel);
        return Response.status(202).entity(updated).build();
    }

    @GET
    @Path("/loadcategory")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<TestModel> loadTestCategory() {
        return testFunction.loadAllTestCategory();
    }

    @GET
    @Path("/loadtest")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<TestModel> loadTest() {
        return testFunction.loadAllTest();
    }

    @GET
    @Path("/categoryhave/{category}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestCategoryHave(@PathParam("category") String category) {
        return testFunction.isTestCategoryHave(category);
    }

    @GET
    @Path("/testhave/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestHave(@PathParam("test") String test) {
        return testFunction.isTestHave(test);
    }

    @GET
    @Path("/category/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCategoryByTest(@PathParam("test") String test) {
        return testFunction.getCategoryByTest(test);
    }

    @GET
    @Path("/testid/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTestIdByTest(@PathParam("test") String test) {
        return testFunction.getTestIdByTest(test);
    }
    
      @GET
    @Path("/testprice/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestPriceByTest(@PathParam("test") String test) {
        return testFunction.getTestPriceByTest(test);
    }

}
