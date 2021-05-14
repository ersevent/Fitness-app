package Exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class trainingSchedule {
    private HashMap<Exercise, String> list;
    private final String muscles = "chest\nshoulder\nback\nbiceps\ntriceps\nlegs";
    DailyPlan monday = new DailyPlan("monday");
    DailyPlan tuesday = new DailyPlan("tuesday");
    DailyPlan wednesday = new DailyPlan("wednesday");
    DailyPlan thursday = new DailyPlan("thursday");
    DailyPlan friday = new DailyPlan("friday");
    DailyPlan saturday = new DailyPlan("saturday");
    DailyPlan sunday = new DailyPlan("sunday");

    public trainingSchedule(){
        list = new HashMap<>();
        loadFromFile();
    }

    public void loadFromFile(){
        String filePath = "src\\Exercise\\Exercises.txt";
        File file = new File(filePath);
        try{
            Scanner sc = new Scanner(file).useDelimiter("\\n");
            String input;
            int id=1;
            while(!(input = sc.next()).trim().isEmpty() && sc.hasNext()){
                addToHashMap("chest", input, id);
                id++;
            }
            id=1;
            while(!(input = sc.next()).trim().isEmpty() && sc.hasNext()){
                addToHashMap("shoulder", input, id);
                id++;
            }
            id=1;
            while(!(input = sc.next()).trim().isEmpty() && sc.hasNext()){
                addToHashMap("back", input, id);
                id++;
            }
            id=1;
            while(!(input = sc.next()).trim().isEmpty() && sc.hasNext()){
                addToHashMap("biceps", input, id);
                id++;
            }
            id=1;
            while(!(input = sc.next()).trim().isEmpty() && sc.hasNext()){
                addToHashMap("triceps", input, id);
                id++;
            }
            id=1;
            while(!(input = sc.next()).trim().isEmpty() && sc.hasNext()){
                addToHashMap("legs", input, id);
                id++;
            }
            sc.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public void addToHashMap(String muscle, String exercise, int id){
        list.put(new Exercise(exercise, id), muscle);
    }

    public void muscleSelector(){
        Scanner in = new Scanner(System.in);
        String muscle;
        do{
            System.out.println("\n\n-------------------");
            System.out.print("Which muscle would you like to train (help for muscle groups, exit for quiting): ");
            muscle = in.next();
            if (muscle.equals("help")) System.out.println(muscles);
            else if(muscle.equals("exit")) return;
            else if(!muscle.equals("chest") && !muscle.equals("shoulder") && !muscle.equals("back") &&
                    !muscle.equals("biceps") && !muscle.equals("triceps") && !muscle.equals("legs")){
                muscle="help";
                System.out.println("Wrong input.");
            }
            else {
                printMuscles(muscle);
            }
        }while(muscle.equals("help"));
        exerciseSelector(muscle);
    }

    public void exerciseSelector(String muscle){
        Scanner in = new Scanner(System.in);
        int number = 0;
        do {
            System.out.print("\nWhich exercise would you like to train (type 0 for exit): ");
            try {
                number = in.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Wrong input.");
            }
            for (Exercise exercise : list.keySet()) {
                if (exercise.getId() == number && list.get(exercise).equals(muscle)) {
                        System.out.print("On which day would you like to train: ");
                        String input = in.next();
                        switch(input){
                            case "monday":
                                monday.addToList(exercise, muscle, sunday, tuesday);
                                break;
                            case "tuesday":
                                tuesday.addToList(exercise, muscle, monday, wednesday);
                                break;
                            case "wednesday":
                                wednesday.addToList(exercise, muscle, tuesday, thursday);
                                break;
                            case "thursday":
                                thursday.addToList(exercise, muscle, wednesday, friday);
                                break;
                            case "friday":
                                friday.addToList(exercise, muscle, thursday, saturday);
                                break;
                            case "saturday":
                                saturday.addToList(exercise, muscle, friday, sunday);
                                break;
                            case "sunday":
                                sunday.addToList(exercise, muscle, saturday, monday);
                                break;
                            default:
                                System.out.println("Please write one day from seven days of the week!");
                        }
                }
            }
        }while(number!=0);
        muscleSelector();
    }

    public void printSchedule(){
        if(monday.isEmptyPlan() && tuesday.isEmptyPlan() && wednesday.isEmptyPlan()
        && thursday.isEmptyPlan() && friday.isEmptyPlan() && saturday.isEmptyPlan()
        && sunday.isEmptyPlan()){
            System.out.println("\nYour training plan is empty.");
        }
        else{
            System.out.println("\nYour training plan:");
            if(!monday.isEmptyPlan()) monday.print();
            if(!tuesday.isEmptyPlan()) tuesday.print();
            if(!wednesday.isEmptyPlan()) wednesday.print();
            if(!thursday.isEmptyPlan()) thursday.print();
            if(!friday.isEmptyPlan()) friday.print();
            if(!saturday.isEmptyPlan()) saturday.print();
            if(!sunday.isEmptyPlan()) sunday.print();
        }
    }

    public void printMuscles(String input){
        for(Exercise exercise : list.keySet()){
            if(list.get(exercise).equals(input)){
                System.out.println(exercise);
            }
        }
    }
}
