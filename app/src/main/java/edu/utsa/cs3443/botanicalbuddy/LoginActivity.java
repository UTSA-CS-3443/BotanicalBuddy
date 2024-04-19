package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import edu.utsa.cs3443.botanicalbuddy.model.LoginCheck;

public class LoginActivity extends AppCompatActivity {

    private String user;
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
        EditText username = findViewById(R.id.usernameText);
        EditText password = findViewById(R.id.passwordText);

        doLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //todo: make it pass the user to the next activity
                user = username.getText().toString();
                pass = password.getText().toString();

                try {
                    if (LoginCheck.validLogin(user, pass, LoginActivity.this)) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ConserveActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Your Username or Password was not correct, try again.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });
    }
}