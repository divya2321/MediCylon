package Server.Staff;

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

@Path("/staff")
public class StaffServer {
    
    StaffFunction staffFunction = StaffFunction.getStaffFunction();
    
    @POST
    @Path("/insertstaff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertStaff(StaffModel staffModel){
        boolean inserted = staffFunction.insertStaff(staffModel);
        return Response.status(202).entity(inserted).build();
    }
    
    @PUT
    @Path("/updatestaff")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStaff(StaffModel staffModel){
        boolean updated = staffFunction.updateStaff(staffModel);
        return Response.status(202).entity(updated).build();
    }
    
    
    @GET
    @Path("/loadstaff")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StaffModel> loadStaff(){
        return staffFunction.loadAllStaff();
    }
    
      @GET
    @Path("/loadstaffidnic")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StaffModel> loadStaffIdNic(){
        return staffFunction.loadAllIdNic();
    }
    
       @GET
    @Path("/loadtechnicianidname")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<StaffModel> loadTechnicianIdNic(){
        return staffFunction.loadAllTechnicianIdName();
    }
    
     @GET
    @Path("/nichave/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isNicHave(@PathParam("nic") String nic){
        return staffFunction.isNicHave(nic);
    }
  
}
