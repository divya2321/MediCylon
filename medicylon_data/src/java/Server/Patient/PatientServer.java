package Server.Patient;

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

@Path("/patient")
public class PatientServer {
    
    PatientFunction patientFunction = PatientFunction.getPatientFunction();
    
    @POST
    @Path("/insertpatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPatient(PatientModel patientModel){
        boolean inserted = patientFunction.insertPatient(patientModel);
        return Response.status(202).entity(inserted).build();
    }
    
    @PUT
    @Path("/updatepatient")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(PatientModel patientModel){
        boolean updated = patientFunction.updatePatient(patientModel);
        return Response.status(202).entity(updated).build();
    }
    
    
    @GET
    @Path("/loadpatient")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<PatientModel> loadPatient(){
        return patientFunction.loadAllPatient();
    }
    
       @GET
    @Path("patientid/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getPatientId(@PathParam("nic") String nic) {

        return patientFunction.getPatientIdByNic(nic);
    }
    
        @GET
    @Path("patientemail/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPatientEmail(@PathParam("nic") String nic) {

        return patientFunction.getPatientEmailByNic(nic);
    }
    
    
}
