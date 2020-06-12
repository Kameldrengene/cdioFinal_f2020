package API;

import controller.UserController;
import dal.IDALException;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    public UserController userController = new UserController();

    @Path("getUserList")
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
    @Path("getRoleList")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<UserDTO> getRole(@QueryParam("role") String role){
        return userController.getRole(role);
    }

    @Path("getActivity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public boolean getUserActivity (@PathParam("id") int id){
        return userController.getActivity(id);
    }

    @Path("activeUser")
    @PUT
    public UserDTO activitySwitchUser(UserDTO user){
        return userController.activitySwitchUser(user);
    }

    @Path("opretUser")
    @POST
    public UserDTO createUser(UserDTO user){
        userController.opretUser(user);
        return user;
    }

    @Path("opdaterUser")
    @PUT
    public UserDTO updateUser(UserDTO user){
        return userController.opdaterUser(user);
    }
}
