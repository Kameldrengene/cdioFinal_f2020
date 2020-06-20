package dal;
import java.sql.SQLException;
import java.util.List;
import dal.dto.UserDTO;

public interface IUserDAO {

    public List<UserDTO> getData() throws SQLException;
    public UserDTO getUser(int userId) throws SQLException;
    public List<UserDTO> getRole(String role) throws SQLException;
    public UserDTO createUser(UserDTO user) throws SQLException;
    public void updateUser(UserDTO user) throws SQLException;
    public boolean getActivity(int id) throws SQLException;
    public void aktivitySwitchUser(int userId) throws SQLException;



}
