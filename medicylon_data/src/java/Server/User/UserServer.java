package Server.User;

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

@Path("/user")
public class UserServer {
    
    UserFunction userFunction = UserFunction.getUserFunction();
    
    @POST
    @Path("/insertuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(UserModel userModel){
        boolean inserted = userFunction.insertUser(userModel);
        return Response.status(202).entity(inserted).build();
    }
    
    @PUT
    @Path("/updateuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserModel userModel){
        boolean updated = userFunction.updateUser(userModel);
        return Response.status(202).entity(updated).build();
    }
    
    
    @GET
    @Path("/loaduser")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserModel> loadUser(){
        return userFunction.loadAllUser();
    }
    
    
    @GET
    @Path("/usernamehave/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isUsernameHave(@PathParam("username") String username){
        return userFunction.isUsernameHave(username);
    }
    
    @POST
    @Path("/checkusernamepass")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkUsernamePassword(UserModel usermodel){
        UserModel userModel = userFunction.checkUsernamePassword(usermodel);
        
        if(userModel != null){
        return Response.status(202).entity(userModel).build();
        }else{
          return Response.status(404).build();  
        }
    }
   
     @PUT
    @Path("/changepassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(UserModel userModel){
        boolean updated = userFunction.chagePassword(userModel);
        return Response.status(202).entity(updated).build();
    }
     
    public static void main(String[] args) {
        UserServer uo = new UserServer();
          UserModel um = new UserModel();;
        
        um.setUsername("nipz2123");
        um.setPassword("Nipun2123");
        
        System.out.println(uo.checkUsernamePassword(um));
    }
    
}
