package Data;
import java.util.List;
import Data.dto.UserDTO;

public interface IUserDAO {

	UserDTO getUser(int userId) throws IDALException.DALException;
	List<UserDTO> getData() throws IDALException.DALException;
	UserDTO createUser(UserDTO user) throws IDALException.DALException;
	void updateUser(UserDTO user) throws IDALException.DALException;
	void aktivitySwitchUser(int userId) throws IDALException.DALException;
	


}
