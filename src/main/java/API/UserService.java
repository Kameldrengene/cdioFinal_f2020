package API;

import Controllers.UserController;
import Data.IDALException;
import Data.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    public UserController userController = new UserController();

    @Path("getUsers")
    @GET
    public List<UserDTO> getData() {
        try {
            return userController.getData();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Path("getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public UserDTO getUser(@PathParam("id") int id) {
        return userController.getUser(id);
    }

    // -Mikkel
    @Path("getRole")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<UserDTO> getRole(@QueryParam("role") String role) throws WebApplicationException {
        return userController.getRole(role);
    }

    @Path("getactivity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public boolean getUserActivity (@PathParam("id") int id){
        return userController.CurrentActivity(id);
    }

    @Path("activeUser")
    @PUT
    public UserDTO activitySwitchUser(UserDTO user){
        return userController.activitySwitchUser(user);
    }

    @Path("createUser")
    @POST
    public UserDTO createUser(UserDTO user){
        userController.createUser(user);
        return user;
    }

    @Path("updateUser")
    @PUT
    public UserDTO updateUser(UserDTO user){
        return userController.updateUser(user);
    }
}
