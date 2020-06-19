package Data;
import java.sql.SQLException;
import java.util.List;
import Data.dto.UserDTO;

public interface IUserDAO {

	UserDTO getUser(int userId) throws SQLException;
	List<UserDTO> getData() throws SQLException;
	UserDTO createUser(UserDTO user) throws SQLException;
	void updateUser(UserDTO user) throws SQLException;
	void aktivitySwitchUser(int userId) throws SQLException;
	


}
