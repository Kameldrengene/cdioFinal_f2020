import dal.IUserDAO;
import dal.UserDAOSQL;
import dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("live")
public class WebService {
    @Path("mysql_json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDAOSQL getData() throws IUserDAO.DALException {
        return new UserDAOSQL();
    }

    @Path("mysql_json/deleteUser/{i}")
    @GET
    public String removeUser(@PathParam("i") int i) throws IUserDAO.DALException {
        UserDAOSQL db = new UserDAOSQL();
        db.deleteUser(i);
        return "User: " + i + " deleted";
    }


    @Path("mysql_json/createUser/{username}/{ini}/{cpr}/{pass}/{role1}/{role2}/{role3}/{role4}")
    @GET
    public String createUser(@PathParam("username") String username,
                             @PathParam("ini") String ini,
                             @PathParam("cpr") String cpr,
                             @PathParam("pass") String pass,
                             @PathParam("role1") String role1,
                             @PathParam("role2") String role2,
                             @PathParam("role3") String role3,
                             @PathParam("role4") String role4) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setIni(ini);
        user.setCpr(cpr);
        user.setPassword(pass);
        if (!role1.equals("null")) {
            user.addRole(role1);
        }
        if (!role2.equals("null")) {
            user.addRole(role2);
        }
        if (!role3.equals("null")) {
            user.addRole(role3);
        }
        if (!role4.equals("null")) {
            user.addRole(role4);
        }
        UserDAOSQL db = new UserDAOSQL();

        try {
            List<UserDTO> data = db.getData();
            user.setUserID(data.get(data.size()-1).getUserID()+1);
            db.createUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        return user.toString();
    }
    /*@Path("mysql_json/createUser")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(UserDTO user) {
        UserDAOSQL db = new UserDAOSQL();

        try {
            List<UserDTO> data = db.getData();
            user.setUserID(data.get(data.size()-1).getUserID()+1);
            db.createUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        return user.toString();
    }*/

    @Path("mysql_json/updateUser/{id}/{username}/{ini}/{cpr}/{pass}/{role1}/{role2}/{role3}/{role4}")
    @GET
    public String updateUser(@PathParam("id") String id,
                             @PathParam("username") String username,
                             @PathParam("ini") String ini,
                             @PathParam("cpr") String cpr,
                             @PathParam("pass") String pass,
                             @PathParam("role1") String role1,
                             @PathParam("role2") String role2,
                             @PathParam("role3") String role3,
                             @PathParam("role4") String role4) {
        UserDTO user = new UserDTO();
        user.setUserID(Integer.parseInt(id));
        user.setUserName(username);
        user.setIni(ini);
        user.setCpr(cpr);
        user.setPassword(pass);
        if (!role1.equals("null")) {
            user.addRole(role1);
        }
        if (!role2.equals("null")) {
            user.addRole(role2);
        }
        if (!role3.equals("null")) {
            user.addRole(role3);
        }
        if (!role4.equals("null")) {
            user.addRole(role4);
        }
        UserDAOSQL db = new UserDAOSQL();

        try {
            db.updateUser(user);
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        return user.toString();
    }

}
