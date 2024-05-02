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


                try {
                    if (LoginCheck.validLogin(user, pass, LoginActivity.this)) {
                        Toast.makeText(LoginActivity.this, "Login Successful, Welcome " + user, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MapActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Your Username or Password was not correct, try again.", Toast.LENGTH_SHORT).show();
                        theRock.setVisibility(View.VISIBLE);
                        AssetFileDescriptor afd = getAssets().openFd("vine-boom.mp3");
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        player.prepare();
                        player.start();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                theRock.setVisibility(View.INVISIBLE);
                                player.stop();
                                player.release();

                            }
                        },2000);   // parameter to show for a particular time


                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        goToHint.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, HintActivity.class);
                startActivity(intent);
            }
        });
    }
    public static String getCurrentUser() {
        return user;
    }
}