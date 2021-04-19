package User;

import User.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class fileWriterUsers {

    public fileWriterUsers(String filePath, HashMap<String, Admin> adminManager, HashMap<String, User> userManager){
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(new File(filePath)));

            if(adminManager!=null && userManager==null){
                for(String username : adminManager.keySet()){
                    bf.write("\n" + username + " " + adminManager.get(username).getPassword() + " " +
                            adminManager.get(username).getName() + " " + adminManager.get(username).getAge()
                            + " " + adminManager.get(username).getGender());
                }
            }
            else if(adminManager==null && userManager!=null){
                for(String username : userManager.keySet()){
                    bf.write("\n" + username + " " + userManager.get(username).getPassword() + " " +
                            userManager.get(username).getName() + " " + userManager.get(username).getAge()
                            + " " + userManager.get(username).getGender());
                }
            }
            bf.close();
        }catch(IOException e){}
    }
}
