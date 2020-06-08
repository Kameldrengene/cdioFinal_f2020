package dal;

import data.MapNonPersistent;
import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAONonPersistent implements IUserDAO {

    MapNonPersistent data;

    public UserDAONonPersistent() {
        data = new MapNonPersistent();
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        return data.getUsers().get(userId);
    }

    @Override
    public List<UserDTO> getData() throws DALException {
       return new ArrayList(data.getUsers().values());
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        if (data.getUsers().containsKey(user.getUserID())){
            throw new DALException("Bruger navn er optaget");
        }
        else
            data.getUsers().put(user.getUserID(),user);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        if (data.getUsers().containsKey(user.getUserID()))
        data.getUsers().replace(user.getUserID(),user);
        else
            throw new DALException("Brugeren eksistet ikke");
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        if (data.getUsers().containsKey(userId))
            data.getUsers().remove(userId);
        else
            throw new DALException("Brugeren eksisterer ikke");
    }
}