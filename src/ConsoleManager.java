import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleManager {


    public ConsoleManager() {
    }

    public void modulSelection(String input) {
        if (input.matches("[Aa]")) {
            new AdminModul();
        } else if (input.matches("[Uu]")) {
            new UserModul();
        } else if (input.matches("[Ee]")) {
            System.exit(0);
        } else {
            System.out.println("Wrong input.");
        }
    }

    public String login(String input) {        //TODO ha nem sikerült a bejelentkezés akkor új felhasználót regisztrálni???
        boolean isMatched = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n-------------------");
        System.out.println("LOGIN\n");
        System.out.print("Username: ");
        String givenUserName = sc.next();
        System.out.print("Password: ");
        String givenUserPassword = sc.next();
        try {
            File file = new File(input);
            Scanner filesc = new Scanner(file);
            while (filesc.hasNextLine()) {
                if (givenUserName.equals(filesc.next()) && givenUserPassword.equals(filesc.next())) isMatched = true;
            }
            filesc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
        if (isMatched) {
            System.out.println("Login succesful.");
            return givenUserName;
        }
        System.err.println("Login failed.");
        return null;
    }
}
