package controller;

import dal.IUserDAO;
import dto.UserDTO;
import bll.IFunctionality;
import pl.IUI;

public class UserLogic {
    
    private IUI tui;
    private IFunctionality functionality;
    private IUserDAO dao;
    
    public UserLogic(IUI tui, IFunctionality functionality, IUserDAO dao){
        this.tui = tui;
        this.functionality = functionality;
        this.dao = dao;
    }
    
    public void start(){
        
        int choice;
        
        //Until the user terminates program
        while(true){
            
            choice = tui.showMenu("Vælg et menupunkt", "Opret ny bruger", "List brugere", "Ret bruger","Slet bruger", "Afslut program");
    
            //Based on menu choice
            switch (choice){
                case 1:
                    createUser();
                    break;
                case 2:
                    ListUsers();
                    break;
                case 3:
                    editUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    System.out.println("\n" + "Programmet lukkes...");
                    java.lang.System.exit(0); //Will exit the program with error code 0
                    break;
            }
        }
    }
    
    //Asks tui to create a new user and dao to save it
    private void createUser(){

        try {
            if(dao.getData().size()==0){
                dao.createUser(tui.createUser(10));
            }
             else {
                UserDTO.setCounter(dao.getData().size()+10);
                dao.createUser(tui.createUser(UserDTO.getCounter()));
            }
        }catch (IUserDAO.DALException e){
            System.out.println("\n" + e.getMessage());
        }
    }
    
    //Asks dao for full userlist and tui to display it
    private void ListUsers(){
        try {
            tui.listUsers(dao.getData());
        } catch (IUserDAO.DALException e) {
            System.out.println("\n" + e.getMessage());
        }
    }
    
    //Enables a user to edit the user database
    private void editUser(){
    
        //Input ID
        int id = tui.getUserID();
        
        try{
            //Check if ID is in database
            validateID(id);
            UserDTO userDTO = dao.getUser(id);
            
            //Show user options
            int choice = tui.showMenu("Vælg hvad du vil redigere", "Navn", "Initialer", "Kodeord", "Roller");
            
            //Based on menu choice
            switch (choice){
                
                //Edit name
                case 1:
                    String newName = tui.inputName();
                    userDTO.setUserName(newName);
                    break;
                    
                //Edit username
                case 2:
                    String newIni = tui.inputInit();
                    userDTO.setIni(newIni);
                    break;
                    
                //Edit password
                case 3:
                    try{
                        String newPassword = tui.inputString("Skriv nyt kodeord: ");
                        functionality.verifyPassword(userDTO, newPassword);
                        userDTO.setPassword(newPassword);
                    }catch(Exception e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                    
                //Edit roles
                case 4:
                    tui.addRolesToUser(userDTO);
                    break;
            }
            
            //Save edit
            dao.updateUser(userDTO);
            
        } catch(userIDNotFound e){
            System.out.println("\n" + e.getMessage());
        } catch (IUserDAO.DALException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
    }
    
    private void deleteUser(){
        
        //Input ID
       int id = tui.getUserID();
       
       try{
           //Check if ID is in database
           validateID(id);
           dao.deleteUser(id);
       } catch (userIDNotFound e){
           System.out.println("\n" + e.getMessage());
       }catch (IUserDAO.DALException e){
           System.out.println("\n" + e.getMessage());
       }
    }
    
    //Checks if ID is in database
    private void validateID(int ID) throws userIDNotFound{
        try {
            //Get user IDs
            int[] IDs = functionality.getUserIDs(dao.getData());
            
            //Check if given ID is in IDs
            if( !(functionality.isUserIDPresent(ID, IDs)) )
                throw new userIDNotFound("ID'et blev ikke fundet");
        } catch (IUserDAO.DALException e) {
            System.out.println("\n" + e.getMessage());;
        }
    }
    
    //Special exception to be thrown
    public class userIDNotFound extends Exception {
        public userIDNotFound(String msg) {
            super(msg);
        }
    }
    
}
