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

    /**
     * Get all users
     * @return Liste over alle bruger
     * @throws WebApplicationException
     */
    @Path("getUsers")
    @GET
    public List<UserDTO> getData() throws WebApplicationException{
        return userController.getData();
    }

    /**
     * Returnerer en Bruger
     * @param id Bruger ID
     * @return En specifik bruger som har angivet ID
     * @throws WebApplicationException
     */
    @Path("getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public UserDTO getUser(@PathParam("id") int id) throws WebApplicationException{
        return userController.getUser(id);
    }

    // -Mikkel

    /**
     * Get all users with specific role
     * @param role Bruger rolle
     * @return Returnerer alle bruger som har en specifik rolle
     * @throws WebApplicationException
     */
    @Path("getRole")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<UserDTO> getRole(@QueryParam("role") String role) throws WebApplicationException {
        return userController.getRole(role);
    }

    /**
     * Create a user
     * @param user User Data Transfer Objekt
     * @return Brugeren der oprettes
     * @throws WebApplicationException
     */
    @Path("createUser")
    @POST
    public UserDTO createUser(UserDTO user) throws WebApplicationException{
        userController.createUser(user);
        return user;
    }

    /**
     * Update user
     * @param user  User Data Transfer Objekt
     * @return created User
     * @throws WebApplicationException
     */
    @Path("updateUser")
    @PUT
    public UserDTO updateUser(UserDTO user) throws WebApplicationException{
        return userController.updateUser(user);
    }

    /**
     * Switch activity of user
     * @param user User Data Transfer Objekt
     * @return User object
     * @throws WebApplicationException
     */
    @Path("activeUser")
    @PUT
    public UserDTO activitySwitchUser(UserDTO user) throws WebApplicationException{
        return userController.activitySwitchUser(user);
    }

    /**
     * Get a users Activity
     * @param id Bruger ID
     * @return Bruger aktitet status
     * @throws WebApplicationException
     */
    @Path("getactivity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public boolean getUserActivity (@PathParam("id") int id) throws WebApplicationException{
        return userController.CurrentActivity(id);
    }


}
