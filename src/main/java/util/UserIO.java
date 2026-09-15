/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.User;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author DIABLO
 */
public class UserIO {
    public synchronized static boolean add(User user, String filepath) {
        File file = new File(filepath);
        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            out.println(user.getEmail() + "|" + user.getFirstName() + "|" + user.getLastName());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public synchronized static User getUser(String email, String filepath) {
        File file = new File(filepath);
        if (!file.exists()) return null;

        try (Scanner in = new Scanner(new BufferedReader(new FileReader(file)))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] tokens = line.split("\\|");
                if (tokens.length >= 3 && tokens[0].equalsIgnoreCase(email)) {
                    User user = new User();
                    user.setEmail(tokens[0]);
                    user.setFirstName(tokens[1]);
                    user.setLastName(tokens[2]);
                    return user;
                }
            }
        } catch (FileNotFoundException e) {
        }
        return null;
    }
}
