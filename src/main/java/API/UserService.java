package API;

import controller.UserController;

import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    public final UserController userController = new UserController();

    //Get all users
    @Path("getUsers")
    @GET
    public List<UserDTO> getData() throws WebApplicationException{
        return userController.getData();
    }

    //Get user with ID
    @Path("getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public UserDTO getUser(@PathParam("id") int id) throws WebApplicationException{
        return userController.getUser(id);
    }

    // -Mikkel
    //Get all users with specific role
    @Path("getRole")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<UserDTO> getRole(@QueryParam("role") String role) throws WebApplicationException {
        return userController.getRole(role);
    }

    //Create user
    @Path("createUser")
    @POST
    public UserDTO createUser(UserDTO user) throws WebApplicationException{
        userController.createUser(user);
        return user;
    }

    //Update user
    @Path("updateUser")
    @PUT
    public UserDTO updateUser(UserDTO user) throws WebApplicationException{
        return userController.updateUser(user);
    }

    //Switch activity of user
    @Path("activeUser")
    @PUT
    public UserDTO activitySwitchUser(UserDTO user) throws WebApplicationException{
        return userController.activitySwitchUser(user);
    }

    //Get a users Activity
    @Path("getactivity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public boolean getUserActivity (@PathParam("id") int id) throws WebApplicationException{
        return userController.CurrentActivity(id);
    }


}
