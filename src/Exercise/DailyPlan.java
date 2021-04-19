package Exercise;

import java.util.ArrayList;

public class DailyPlan {
    protected boolean isChestNextDayAvailable = true;
    protected boolean isBackNextDayAvailable = true;
    protected boolean isShoulderNextDayAvailable = true;
    protected boolean isBicepsNextDayAvailable = true;
    protected boolean isTricepsNextDayAvailable = true;
    protected boolean isLegsNextDayAvailable = true;
    protected ArrayList<Exercise> dailyPlan;
    String name;

    public DailyPlan(String name){
        dailyPlan = new ArrayList<>();
        this.name=name;
    }

    public void addToList(Exercise exercise, String muscle, DailyPlan previousDay, DailyPlan nextDay){
        switch(muscle){
            case "chest":
                if(previousDay.isChestNextDayAvailable && nextDay.isChestNextDayAvailable){
                    if(dailyPlan.contains(exercise)){
                        System.out.println(alreadyContains());
                    }
                    else{
                        dailyPlan.add(exercise);
                        this.isChestNextDayAvailable=false;
                    }
                }
                else{
                    System.out.println(multipleDaysError());
                }
                break;
            case "shoulder":
                if(previousDay.isShoulderNextDayAvailable && nextDay.isShoulderNextDayAvailable){
                    if(dailyPlan.contains(exercise)){
                        System.out.println(alreadyContains());
                    }
                    else{
                        dailyPlan.add(exercise);
                        this.isShoulderNextDayAvailable=false;
                    }
                }
                else{
                    System.out.println(multipleDaysError());
                }
                break;
            case "back":
                if(previousDay.isBackNextDayAvailable && nextDay.isBackNextDayAvailable){
                    if(dailyPlan.contains(exercise)){
                        System.out.println(alreadyContains());
                    }
                    else{
                        dailyPlan.add(exercise);
                        this.isBackNextDayAvailable=false;
                    }
                }
                else{
                    System.out.println(multipleDaysError());
                }
                break;
            case "biceps":
                if(previousDay.isBicepsNextDayAvailable && nextDay.isBicepsNextDayAvailable){
                    if(dailyPlan.contains(exercise)){
                        System.out.println(alreadyContains());
                    }
                    else{
                        dailyPlan.add(exercise);
                        this.isBicepsNextDayAvailable=false;
                    }
                }
                else{
                    System.out.println(multipleDaysError());
                }
                break;
            case "triceps":
                if(previousDay.isTricepsNextDayAvailable && nextDay.isTricepsNextDayAvailable){
                    if(dailyPlan.contains(exercise)){
                        System.out.println(alreadyContains());
                    }
                    else{
                        dailyPlan.add(exercise);
                        this.isTricepsNextDayAvailable=false;
                    }
                }
                else{
                    System.out.println(multipleDaysError());
                }
                break;
            case "legs":
                if(previousDay.isLegsNextDayAvailable && nextDay.isLegsNextDayAvailable){
                    if(dailyPlan.contains(exercise)){
                        System.out.println(alreadyContains());
                    }
                    else{
                        dailyPlan.add(exercise);
                        this.isLegsNextDayAvailable=false;
                    }
                }
                else{
                    System.out.println(multipleDaysError());
                }
                break;
            default:
                System.out.println("Wrong input.");
        }
    }

    public void print() {
        System.out.println("    " + name + ":");
        for (Exercise exercise : dailyPlan) {
            System.out.println(exercise.getName());
        }
    }

    public boolean isEmptyPlan(){
        return dailyPlan.isEmpty();
    }

    public String alreadyContains(){
        return "Training plan already contains the exercise on " + name + ".";
    }

    public String multipleDaysError(){
        return "You can't train legs multiple days in a row.";
    }
}
