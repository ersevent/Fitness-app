package Exercise;

public class Exercise {
    private String name;
    private int id;

    public Exercise(String name, int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return id + " " + name;
    }
}
