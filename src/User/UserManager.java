package User;

import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
    private HashMap<String, User> userManager;
    private HashMap<String, Admin> adminManager;
    private boolean permission;

    public UserManager(Boolean permission){
        this.permission=permission;
        if(permission) {adminManager = new HashMap();}
        else{userManager = new HashMap<>();}
    }

    public void addUser(Boolean isAdmin){
        System.out.println("\n\n-------------------");
        System.out.println("Registration\n");
        Scanner sc = new Scanner(System.in);
        boolean isOkay = false;
        String username = null;

        while(!isOkay){
            isOkay=true;
            System.out.print("Username: ");
            username = sc.next();
            if(isAdmin){
                for(String existingUsername : adminManager.keySet()){
                    if(existingUsername.equals(username)) isOkay=false;
                }
            }
            if(!isAdmin){
                for(String existingUsername : userManager.keySet()){
                    if(existingUsername.equals(username)) isOkay=false;
                }
            }
            if(!isOkay) System.out.println("Username not available.\n");
        }
        System.out.print("Password: ");
        String password = sc.next();
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Gender: ");
        String gender = sc.next();
        System.out.println("User has been created.");

        if(isAdmin) {
            addAdminToManager(username, new Admin(username, password, name, age, gender));
            fileWriterUsers fw = new fileWriterUsers("src\\User\\Admins.txt", getAdminManager(), null);
        }
        else{
            addUserToManager(username, new User(username, password, name, age, gender));
            fileWriterUsers fw = new fileWriterUsers("src\\User\\Users.txt", null, getUserManager());
        }
    }

    public void addUserToManager(String username, User user){
        userManager.put(username, user);
    }


    public void addAdminToManager(String username, Admin admin){
        adminManager.put(username, admin);
    }


    public void delete(Boolean isAdmin){
        System.out.println("\n\n-------------------");
        System.out.print("Give me the username which one you would like to delete: ");
        Scanner sc = new Scanner(System.in);
        String givenUsername = sc.next();
        if(isAdmin){
            for(String username : adminManager.keySet()){
                if(givenUsername.equals(username)) {
                    adminManager.remove(username);
                    fileWriterUsers fw = new fileWriterUsers("src\\User\\Admins.txt", getAdminManager(), null);
                    System.out.println("User has been deleted.");
                    return;
                }
            }
        }
        else{
            for(String username : userManager.keySet()){
                if(givenUsername.equals(username)) {
                    userManager.remove(username);
                    fileWriterUsers fw = new fileWriterUsers("src\\User\\Users.txt", null, getUserManager());
                    System.out.println("User has been deleted.");
                    return;
                }
            }
        }
        System.out.println("This username is not found.");
    }

    public void changeData(String givenUsername, String whichData){
        for(User user : userManager.values()){
            if(givenUsername.equals(user.getUserName())) {
                switch(whichData){
                    case "name":
                        user.setName();
                        System.out.println("User's data has been updated.");
                        break;
                    case "password":
                        user.setPassword();
                        System.out.println("User's data has been updated.");
                        break;
                    case "age":
                        user.setAge();
                        System.out.println("User's data has been updated.");
                        break;
                    case "gender":
                        user.setGender();
                        System.out.println("User's data has been updated.");
                        break;
                    case "delete":
                        userManager.remove(givenUsername);
                        fileWriterUsers fw = new fileWriterUsers("src\\User\\Users.txt", null, getUserManager());
                        System.out.println("Profile deleted.");
                        return;
                }
            }
        }
        fileWriterUsers fw = new fileWriterUsers("src\\User\\Users.txt", null, getUserManager());
    }

    public HashMap<String, User> getUserManager() {
        return userManager;
    }

    public HashMap<String, Admin> getAdminManager() {
        return adminManager;
    }

    public boolean doesItContains(String givenUsername){
        return userManager.containsKey(givenUsername);
    }

    public String toString(){
        if(permission){
            return adminManager.toString();
        }
        return userManager.toString();
    }

}
