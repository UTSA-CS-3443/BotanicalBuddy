package edu.utsa.cs3443.botanicalbuddy.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import edu.utsa.cs3443.botanicalbuddy.LoginActivity;
import edu.utsa.cs3443.botanicalbuddy.RegistrationActivity;


public class LoginCheck {
    private String username;
    private String password;
    private String hint;

    public static void addAccount(String username, String password, String hint, RegistrationActivity activity) throws IOException {
        try {
            String content = username + "," + password + "," + hint;
            File file = new File("accounts.csv");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean validLogin(String username, String password, LoginActivity activity) throws IOException {
        AssetManager manager = activity.getAssets();

        InputStream csvFile = manager.open("accounts.csv");
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
