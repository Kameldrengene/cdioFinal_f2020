package controller;

import dal.IUserDAO;
import dal.UserDAOSQL;
import dal.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("live")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebService {
    @Path("getUsers")
    @GET
    public UserDAOSQL getData() throws IUserDAO.DALException {
        return new UserDAOSQL();
    }

    @Path("activeUser")
    @POST
    public UserDTO activitySwitchUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.aktivitySwitchUser(user.getUserID());
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return user;
    }

    @POST
    public UserDTO createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.createUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Path("updateUser")
    @POST
    public UserDTO updateUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();
        try {
            db.updateUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        return user;
    }


}
