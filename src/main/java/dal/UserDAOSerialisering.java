package dal;

import data.MapSerialisering;
import dto.UserDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOSerialisering implements IUserDAO{
    
    MapSerialisering data;

    
    public UserDAOSerialisering(){
        data = readUserList();
        if(data==null){
            MapSerialisering newList = new MapSerialisering();
            data= newList;
            writeUserList(data);
            readUserList();
        }
    }
    
    public MapSerialisering readUserList(){
        MapSerialisering temp = null;
        try{
            FileInputStream fileInputStream = new FileInputStream("src/main/java/Data/users.data");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            temp = (MapSerialisering) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch (Exception e){
            System.out.println("Filen findes ikke");
        }
        return temp;
    }

    public void writeUserList(MapSerialisering savethisList){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/Data/users.data");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(savethisList);
            objectOutputStream.flush();
            fileOutputStream.close();
            objectOutputStream.close();
        }catch (Exception e){
            System.out.println("Kunne ikke sktive");
        }
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
            throw new DALException("\n" + "Bruger ID er optaget");
        }
        else {
            data.getUsers().put(user.getUserID(), user);
            writeUserList(data);
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        if (data.getUsers().containsKey(user.getUserID())) {
            data.getUsers().replace(user.getUserID(), user);
            writeUserList(data);
        }
        else
            throw new DALException("\n" + "Brugeren eksistet ikke");
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        if (data.getUsers().containsKey(userId)) {
            data.getUsers().remove(userId);
            writeUserList(data);
        }
        else
            throw new DALException("\n" + "Brugeren eksisterer ikke");

    }
}