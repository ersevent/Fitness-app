package User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class User {
    private String name;
    private int age;
    private String gender;
    private String userName;
    private String password;

    public User(String userName, String password, String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
    }

    public User(){}

    public void loadFromFile(UserManager users, String input) {
        File file = new File(input);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String userName = sc.next();
                users.addUserToManager(userName, new User(userName, sc.next(), sc.next(), sc.nextInt(), sc.next()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }

    public void setName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Give me a new name: ");
        String newName = sc.next();
        this.name=newName;
}

    public void setPassword(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Give me a new password: ");
        String newPassword = sc.next();
        this.password=newPassword;
    }

    public void setAge(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Give me a new age: ");
        int newAge = sc.nextInt();
        this.age=newAge;
    }

    public void setGender(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Give me a new gender: ");
        String newGender = sc.next();
        this.gender = newGender;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return " User's name: " + getName() + ", age: " + getAge() + ", gender:" + getGender();
    }
}
