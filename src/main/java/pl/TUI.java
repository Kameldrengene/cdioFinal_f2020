package pl;

import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class TUI implements IUI {
    
    private Scanner scan = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public int showMenu(String message, String... menuItems){
        System.out.println("\n" + "#########################################");
        System.out.println(message);
        
        //Display menu
        for (int i = 1; i < menuItems.length+1; i++) {
            System.out.println(i + ". " + menuItems[i-1]);
        }
    
        System.out.println("#########################################" + "\n");
        
        int choice = 0;
        
        //Until user correctly chooses item from menu
        while(true){
            choice = inputNumber();
            
            if(choice < 1 || choice > menuItems.length)
                System.out.println("\n" + "Indtast venligst et nummer fra listen:");
            else
                break;
        }
        return choice;
    }
    
    //Keeps prompting user for number until one is received
    public int inputNumber(){
            int choice;
        
            while(true){
                try{
                    choice = Integer.parseInt(scan.nextLine());
                    break;
                } catch(NumberFormatException e){
                    System.out.println("\n" + "Indtast venligst et tal: ");
                }
            }
            return choice;
    }

    public String inputName() {
        String name = "";
        do {
            System.out.println("\n" + "Indtast navn: ");
            name = scan.nextLine();
            if (name.equals("")){
                System.out.print("\n" + "Brugeren skal have et navn");
            }
        } while (name.equals(""));
        return name;
    }

    public String inputInit() {
        String init = "";
        boolean check = false;
        do {
            System.out.println("\n" + "Indtast Initial: ");
            init = scan.nextLine();
            if (init.length() < 2 || init.length() > 4) {
                System.out.print("\n" + "Initialer skal have mellem 2 - 4 bogstaver");
            } else {
                check = true;
            }
        } while (!check);
        return init;
    }

    public String inputCPR() {
        String cpr = "";
        boolean check = false;
        do {
            System.out.println("\n" + "Indtast brugerens CPR-nummer i formattet: DDMMYYxxxx");
            cpr = scan.nextLine();
            if (cpr.length() != 10) {
                System.out.println("Forkert format");
            } else {
                try {
                    int dd = Integer.parseInt(cpr.substring(0, 2));
                    int mm = Integer.parseInt(cpr.substring(2, 4));
                    int yy = Integer.parseInt(cpr.substring(4, 6));
                    int xxxx = Integer.parseInt(cpr.substring(6, 10));
                    check = true;
                } catch (NumberFormatException e) {
                    System.out.println("Forkert format");
                }
            }
        } while (!check);
        return cpr;
    }

    public void addRolesToUser(UserDTO user) {
        String inp = "";
        System.out.println("\n" + "Vælg Roler (skriv tallene for alle de roller brugeren skal have på samme linje):");
        System.out.println("1:Admin, 2:Pharmacist, 3:Foreman, 4:Operator");
        
        //Remove previously attached roles to user
        user.removeRoles();
        
        //Let user choose new roles and add them
        inp = scan.nextLine();
        for (int i = 0; i < inp.length(); i++) { // Will not be able to have more than 10 different roles this way.
            try {
                int x = Character.getNumericValue(inp.charAt(i));
                switch (x) {
                    case 1:
                        user.addRole("Admin");
                        break;
                    case 2:
                        user.addRole("Pharmacist");
                        break;
                    case 3:
                        user.addRole("Foreman");
                        break;
                    case 4:
                        user.addRole("Operator");
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public UserDTO createUser(int id) {

        UserDTO newuser = new UserDTO();

        String name = inputName();
        newuser.setUserName(name);

        String init = inputInit();
        newuser.setIni(init);

        newuser.setCpr(inputCPR());

        addRolesToUser(newuser);

        newuser.setUserID(id+1);

        System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");
        System.out.println("¤ Brugeren er oprettet ¤");
        System.out.println("¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤");

        return newuser;
    }

    public void listUsers(List<UserDTO> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
    
    //Keeps prompting user for an ID until a valid one has been entered
    public int getUserID(){
        
        System.out.println("\n" + "Indtast ID: ");
        int id;
        
        while(true){
            id = inputNumber();
            if(id >= 11 && id <= 99)
                break;
            else
                System.out.println("\n" + "Indtast et ID fra 11-99:");
        }
        return id;
    }
    
    //Displays a message and prompts user for string
    public String inputString(String message){
        System.out.println(message);
        return scan.nextLine();
    }
}
