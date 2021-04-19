import java.util.Scanner;

public class Fitness_app {
    public static void main(String[] args) {
        ConsoleManager console = new ConsoleManager();

        while (true) {
            System.out.println("\n-------------------");
            System.out.println("Please select your modul!");
            System.out.println("\na: admin\nu: user\ne: exit");
            Scanner in = new Scanner(System.in);
            String input = in.next();
            console.modulSelection(input);
        }
    }
}
