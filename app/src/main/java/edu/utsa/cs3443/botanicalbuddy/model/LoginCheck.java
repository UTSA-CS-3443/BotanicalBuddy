package edu.utsa.cs3443.botanicalbuddy.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;


public class LoginCheck {
    private String username;
    private String password;
    private String hint;

    public static boolean addAccount(String username, String password, String hint, Context context) throws IOException {

        File accounts = new File(context.getFilesDir(), "accounts.csv");
        if (!accounts.exists()){
            accounts.createNewFile();
        }

        try {
            FileInputStream csvFile = new FileInputStream(accounts);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile));
            String content = username + "," + password + "," + hint + "\n";
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split(",");
                if (data[0].equals(username)){
                    reader.close();
                    return false;
                }
            }
            FileWriter fw = new FileWriter(accounts.getAbsoluteFile(), true);
            fw.append(content);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getHint(String username, Context context) throws IOException {
        File accounts = new File(context.getFilesDir(), "accounts.csv");
        if (!accounts.exists()){
            accounts.createNewFile();
        }

        FileInputStream csvFile = new FileInputStream(accounts);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    return "Your hint is: " + data[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Your hint is: N/A";
    }

    public static Boolean validLogin(String username, String password, Context context) throws IOException {
        File accounts = new File(context.getFilesDir(), "accounts.csv");
        if (!accounts.exists()){
            accounts.createNewFile();
        }
        FileInputStream csvFile = new FileInputStream(accounts);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    if (data[1].equals(password)){
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

}
