package edu.utsa.cs3443.botanicalbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import edu.utsa.cs3443.botanicalbuddy.model.LoginCheck;

/**
 * @author Devin Dunn
 * Gives you the pw hint
 */
public class HintActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hint);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button checkHint = findViewById(R.id.checkHint);
        EditText usernameText = findViewById(R.id.usernameText);
        TextView hintText = findViewById(R.id.hintText);


        //checks if the username is valid, and if it is changes the text above to what was set as a password hint
        checkHint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                if (username.isEmpty()){
                    hintText.setText("Please enter a valid username");
                } else {
                    try {
                        hintText.setText(LoginCheck.getHint(username, HintActivity.this));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}