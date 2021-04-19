import Product.*;
import User.*;

import java.util.Scanner;

public class AdminModul implements Modul {
        ConsoleManager cm = new ConsoleManager();
        String adminFilePath;
        String userFilePath;

    public AdminModul() {
        cm = new ConsoleManager();
        adminFilePath = "src\\User\\Admins.txt";
        userFilePath = "src\\User\\Users.txt";
        String givenUsername = cm.login(adminFilePath);

        if (givenUsername != null) {
            UserManager admins = new UserManager(true);
            Admin admin = new Admin();
            admin.loadFromFile(admins, adminFilePath);

            UserManager users = new UserManager(false);
            User user = new User();
            user.loadFromFile(users, userFilePath);

            ProductManager products = new ProductManager();
            Product product = new Product();
            product.loadFromFile(products);

            Scanner in = new Scanner(System.in);
            String input = null;
            while(input!="exit") {
                System.out.println("\n\n-------------------");
                System.out.println("What do you want to do?");
                System.out.print("Give me a command (help for commandlist): ");

                input = in.next();
                switch (input) {
                    case "adda":
                        admins.addUser(true);
                        break;
                    case "dela":
                        admins.delete(true);
                        break;
                    case "addu":
                        users.addUser(false);
                        break;
                    case "delu":
                        users.delete(false);
                        break;
                    case "changeu":
                        changeUserData(users);
                        break;
                    case "aptl":
                        products.addProductAdmin();
                        break;
                    case "rpfl":
                        products.deleteProductAdmin();
                        break;
                    case "help":
                        System.out.println(commandList());
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Wrong input.");
                }
            }
        }
    }

    public void changeUserData(UserManager users){
        System.out.print("Give me which user's data would you like to change: ");
        Scanner sc = new Scanner(System.in);
        String givenUsername = sc.next();
        if (!users.doesItContains(givenUsername)) {
            System.out.println("Username not found!");
            return;
        }
        System.out.println("Which data would you like to change for " + givenUsername + "?");
        System.out.println("changeun: change " + givenUsername + "'s name\n" +
                "changeup: change " + givenUsername + "'s password\n" +
                "changeua: change " + givenUsername + "'s age\n" +
                "changeug: change " + givenUsername + "'s gender\n" +
                "exit: quit from data changer");

        String input;
        boolean isLooped;
        do {
            input = sc.next();
            switch (input) {
                case "changeun":
                    users.changeData(givenUsername, "name");
                    isLooped = false;
                    break;
                case "changeup":
                    users.changeData(givenUsername, "password");
                    isLooped = false;
                    break;
                case "changeua":
                    users.changeData(givenUsername, "age");
                    isLooped = false;
                    break;
                case "changeug":
                    users.changeData(givenUsername, "gender");
                    isLooped = false;
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Wrong input.");
                    System.out.print("Which data would you like to change for " + givenUsername + ": ");
                    isLooped = true;
            }
        } while (isLooped);
    }

    public String commandList(){
        return "adda: add admin\naddu: add user\ndela: delete admin\ndelu: delete user\nchangeu: change user's data\naptl: add product to list\nrpfl: remove product from list\nexit: exit from the modul";
    }
}

