package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import edu.utsa.cs3443.botanicalbuddy.model.DestinationList;

public class MapActivity extends AppCompatActivity {

    DestinationList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);


            /* try {
                list.loadDestinations(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            */

        Button button32 = findViewById(R.id.button32);
        ImageView logo = findViewById(R.id.main_logo);
        ImageView menu = findViewById(R.id.dropdown_menu);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("awdawd", "button press");
                startActivity(new Intent(MapActivity.this, ConserveActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapActivity.this, MainActivity.class));
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapActivity.this, "clicked menu", Toast.LENGTH_SHORT).show();
            }
        });


    }
}