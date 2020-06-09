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
    @GET
    public List<UserDTO> getData() {
        try {
            return userController.getData();
        } catch (IDALException.DALException e) {
            e.printStackTrace();
        }
        return null;
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
