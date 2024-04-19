package edu.utsa.cs3443.botanicalbuddy.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.content.res.AssetManager;

import edu.utsa.cs3443.botanicalbuddy.LoginActivity;


public class LoginCheck {
    private String username;
    private String password;

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
