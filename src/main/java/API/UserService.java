package API;

import controller.UserController;
import dal.IDALException;
import dal.IUserDAO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    public UserController userController = new UserController();

    @Path("getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<UserDTO> getData() {
        try {
            return userController.getData().getData();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
    }

    // -Mikkel
    @Path("getRole")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<UserDTO> getRole(@QueryParam("role") String role){
        System.out.println(role);
        return userController.getAdmins(role);
    }



    @Path("activeUser")
    @POST
    public UserDTO activitySwitchUser(UserDTO user){
        return userController.activitySwitchUser(user);
    }
    @POST
    public UserDTO createUser(UserDTO user){
        return userController.createUser(user);
    }
    @Path("updateUser")
    @POST
    public UserDTO updateUser(UserDTO user){
        return userController.updateUser(user);
    }
}
