package Calorie;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class calorieCalculator {
    private final String categories = "cereal\neggs\nmeat\nmushrooms\nvegetables";
    private double calories=0.0;
    private double proteins=0.0;
    private double fat=0.0;
    private double carbohydrates=0.0;
    private double grams;
    private double previousCalories;
    private double previousProtein;
    private double previousFat;
    private double previousCarbohydrates;
    private boolean repeat;
    private String filePath = "src\\Calorie\\CalorieTable.txt";
    DecimalFormat df = new DecimalFormat("#.#");

    public calorieCalculator() {

    }

    public void calculator(){
        Scanner sc = new Scanner(System.in);

        String input;
        String categorie;
        boolean isMatched = false;
        do {
            do {
                System.out.println("\n\n-------------------");
                System.out.print("Please give me the categorie of the good (help for categories, exit for quiting): ");
                input = sc.next();
                if (input.equals("help")) {
                    System.out.println(categories);
                } else if (!input.equals("cereal") && !input.equals("eggs") && !input.equals("meat")
                        && !input.equals("mushrooms") && !input.equals("vegetables") && !input.equals("exit")) {
                    System.out.println("Wrong input.");
                    input = "help";
                }
            } while (input.equals("help"));
            categorie = input;
            if(!input.equals("exit")) {
                try {
                    do {
                        Scanner in = new Scanner(new File(filePath));
                        System.out.print("What kind of food did you eat (help for foods): ");
                        input = sc.next();
                        if (input.equals("help")) {
                            while (in.hasNext()) {
                                in.nextLine();
                                if (in.hasNext()) {
                                    if (in.next().equals(categorie)) {
                                        System.out.println(in.next());
                                    }
                                }
                            }
                            in.close();
                        } else {
                            while (in.hasNext()) {
                                in.nextLine();
                                in.next();
                                while (in.hasNext()) {
                                    if (in.next().equals(input)) {
                                        isMatched = true;
                                        System.out.print("How many grams did you eat: ");
                                        grams = sc.nextDouble();
                                        previousCalories=(grams/100)*Double.parseDouble(in.next());
                                        calories += previousCalories;
                                        previousProtein=(grams/100)*Double.parseDouble(in.next());
                                        proteins += previousProtein;
                                        previousFat=(grams/100)*Double.parseDouble(in.next());
                                        fat += previousFat;
                                        previousCarbohydrates=(grams/100)*Double.parseDouble(in.next());
                                        carbohydrates += previousCarbohydrates;
                                    }
                                }
                                if (!isMatched) {
                                    System.out.println("Wrong input.");
                                    input = "help";
                                }
                            }
                            in.close();
                        }
                    } while (input.equals("help"));
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }

                print();
                do {
                    System.out.print("Do you want to undo (yes/no): ");
                    repeat=true;
                    input=sc.next();
                    if (input.equals("yes")) {
                        undo();
                        repeat=false;
                        print();
                    }
                    else if(input.equals("no")){
                        repeat=false;
                    }
                    else {
                        System.out.println("Wrong input.");
                    }
                }while(repeat);
            }
        }while(!input.equals("exit"));
    }

    public void clear(){
        calories=0;
        proteins=0;
        fat=0;
        carbohydrates=0;
    }

    public void undo(){
        calories-=previousCalories;
        proteins-=previousProtein;
        fat-=previousFat;
        carbohydrates-=previousCarbohydrates;
    }

    public void print(){
        System.out.println("Calories: " + df.format(calories) +
                "kcal, proteins: " + df.format(proteins) + "g, fat: " + df.format(fat)
                + "g, carbohydrates: " + df.format(carbohydrates) + "g");
    }
}
