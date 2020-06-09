package API;

import controller.UserService;
import dal.IUserDAO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserAPI {
    public UserService userService = new UserService();
    @Path("getUsers")
    @GET
    public UserDAOSQL getData() {
        try {
            return userService.getData();
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Path("activeUser")
    @POST
    public UserDTO activitySwitchUser(UserDTO user){
        return userService.activitySwitchUser(user);
    }
    @POST
    public UserDTO createUser(UserDTO user){
        return userService.createUser(user);
    }
    @Path("updateUser")
    @POST
    public UserDTO updateUser(UserDTO user){
        return userService.updateUser(user);
    }
}
