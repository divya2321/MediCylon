package Server.Staff;

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
@Path("/staff")
public class StaffOperation {

    @POST
    @Path("/insertstaff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertStaff(StaffModel staffModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/staff/insertstaff");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(staffModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("/updatestaff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateStaff(StaffModel staffModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget webTarget = client.target(getURI()).path("data/staff/updatestaff");
        Response response = webTarget.request().put(Entity.entity(staffModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @GET
    @Path("/loadstaff")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StaffModel> loadStaff() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<StaffModel>> genericType = new GenericType<ArrayList<StaffModel>>() {
        };
        ArrayList<StaffModel> returnStaffList = client.target(getURI())
                .path("data/staff/loadstaff").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }

    @GET
    @Path("/loadstaffidnic")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StaffModel> loadStaffIdNic() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<StaffModel>> genericType = new GenericType<ArrayList<StaffModel>>() {
        };
        ArrayList<StaffModel> returnStaffList = client.target(getURI())
                .path("data/staff/loadstaffidnic").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }

    @GET
    @Path("/loadtechnicianidname")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StaffModel> loadTechnicianIdName() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        GenericType<ArrayList<StaffModel>> genericType = new GenericType<ArrayList<StaffModel>>() {
        };
        ArrayList<StaffModel> returnStaffList = client.target(getURI())
                .path("data/staff/loadtechnicianidname").request().accept(MediaType.APPLICATION_JSON).get(genericType);
        return returnStaffList;
    }

    @GET
    @Path("/nichave/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isNicHave(@PathParam("nic") String nic) {
        Client client = ClientBuilder.newClient(new ClientConfig());

        boolean returnboolean = client.target(getURI())
                .path("data/staff/nichave").path(nic).request().accept(MediaType.TEXT_PLAIN).get(Boolean.class);
        return returnboolean;
    }

    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost:8080/medicylon_data").build();
    }

    public static void main(String[] args) {

        StaffModel staffModel = new StaffModel();
        staffModel.setNic("985623560V");
        staffModel.setFname("Anura");
        staffModel.setLname("Kumara");
        staffModel.setContact1("0775623600");
        staffModel.setEmail("anura.kumara@gmail.com");
        staffModel.setNo("541/2");
        staffModel.setStreet1("Kadahapola");
        staffModel.setCity("Mahara");
        staffModel.setJob("technician");
        staffModel.setStatus("active");
//        staffModel.setStaffId(2);

        System.out.println(new StaffOperation().insertStaff(staffModel));
    }

}
