package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import edu.utsa.cs3443.botanicalbuddy.model.LoginCheck;

public class LoginActivity extends AppCompatActivity {

    private static String user;
    private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button doLogin = findViewById(R.id.doLogin);
        TextView goToHint = findViewById(R.id.goToHint);
        Button goToRegister = findViewById(R.id.goToRegister);
        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);
        ImageView theRock = findViewById(R.id.theRock);

        doLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //todo: make it pass the user to the next activity
                user = username.getText().toString();
                pass = password.getText().toString();

                //passes the username and password entered to the logincheck class and if it is correct passes the user to the next activity
                //if the password is incorrect you get prompted to try again
                try {
                    if (LoginCheck.validLogin(user, pass, LoginActivity.this)) {
                        Toast.makeText(LoginActivity.this, "Login Successful, Welcome " + user, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MapActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Your Username or Password was not correct, try again.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        //takes the user to the registration page
        goToRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        //takes the user to the hint page
        goToHint.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, HintActivity.class);
                startActivity(intent);
            }
        });
    }
    //a call the app uses to get the current user for all future pages
    public static String getCurrentUser() {
        return user;
    }
}