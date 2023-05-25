package Server.PatientSystemLogin;

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

@Path("/patientlogin")
public class PatientLoginServer {
    
    PatientLoginFunction patientLoginFunction = PatientLoginFunction.getPatientLoginFunction();
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkIdPassword(PatientLoginModel patientLoginModel){
        String patientName = patientLoginFunction.checkIdPassword(patientLoginModel);
        return Response.status(202).entity(patientName).build();
    }
    
    
    
}
