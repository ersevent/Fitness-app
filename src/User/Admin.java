package User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String userName, String password, String name, int age, String gender) {
        super(userName, password, name, age, gender);
    }

    public Admin(){}

    @Override
    public void loadFromFile(UserManager admins, String input) {
        File file = new File(input);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String userName = sc.next();
                admins.addAdminToManager(userName, new Admin(userName, sc.next(), sc.next(), sc.nextInt(), sc.next()));
            }
            sc.close();
        } catch(FileNotFoundException e){
            System.err.println("File not found!");
        }
    }

    @Override
    public String toString(){
        return " Admin's name: " +  getName() + ", age: " + getAge() + ", gender:" + getGender();
    }
}
