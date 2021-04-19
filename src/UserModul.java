import Product.*;
import User.*;
import Calorie.*;
import Exercise.*;
import java.util.Scanner;

public class UserModul implements Modul{
    ConsoleManager cm;
    String userFilePath;
    String givenUserName;

    public UserModul(){
        cm = new ConsoleManager();
        userFilePath = "src\\User\\Users.txt";
        givenUserName = cm.login(userFilePath);

        if (givenUserName != null) {
            UserManager users = new UserManager(false);
            User user = new User();
            user.loadFromFile(users, userFilePath);

            ProductManager products = new ProductManager();
            Product product = new Product();
            product.loadFromFile(products);
            Cart cart = new Cart();

            calorieCalculator cc = new calorieCalculator();

            trainingSchedule exercise = new trainingSchedule();

            Scanner in = new Scanner(System.in);
            String input = null;

            while(input!="exit") {
                System.out.println("\n\n-------------------");
                System.out.println("What do you want to do?");
                System.out.print("Give me a command (help for commandlist): ");

                input = in.next();
                switch (input) {
                    case "changeu":
                        changeUserData(users);
                        break;
                    case "delu":
                        users.changeData(givenUserName, "delete");
                        break;
                    case "addprod":
                        cart = products.addToCart(cart);
                        break;
                    case "cart":
                        cart.whatIsInTheCart();
                        break;
                    case "remprod":
                        cart.deleteFromCart();
                        break;
                    case "buy":
                        cart.buy(products);
                        break;
                    case "kcalcal":
                        cc.calculator();
                        break;
                    case "kcalprint":
                        cc.print();
                        break;
                    case "kcalclear":
                        cc.clear();
                        break;
                    case "schedule":
                        exercise.muscleSelector();
                        break;
                    case "printplan":
                        exercise.printSchedule();
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


    @Override
    public void changeUserData(UserManager users) {
        System.out.println("Which data would you like to change?");
        System.out.println("changen: change name\nchangep: change password\n" +
                "changea: change age\nchangeg: change gender\n" +
                "exit: quit from data changer");

        Scanner in = new Scanner(System.in);
        String input;
        boolean isLooped;

        do {
            input = in.next();
            switch (input) {
                case "changen":
                    users.changeData(givenUserName, "name");
                    isLooped = false;
                    break;
                case "changep":
                    users.changeData(givenUserName, "password");
                    isLooped = false;
                    break;
                case "changea":
                    users.changeData(givenUserName, "age");
                    isLooped = false;
                    break;
                case "changeg":
                    users.changeData(givenUserName, "gender");
                    isLooped = false;
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Wrong input.");
                    System.out.print("Which data would you like to change: ");
                    isLooped = true;
            }
        } while (isLooped);
    }

    public String commandList(){
        return "changeu: change user's data\ndelu: delete user\naddprod: add product to cart\n" +
                "cart: what is in the cart\nremprod: remove product from the cart\n" +
                "buy: finalize purchase\nkcalcal: calorie calculator\nkcalprint: print out the result\n" +
                "kcalclear: clear the calculator's data\nschedule: create a training plan\n" +
                "printplan: print out the plan\nexit: exit from the modul";
    }
}
