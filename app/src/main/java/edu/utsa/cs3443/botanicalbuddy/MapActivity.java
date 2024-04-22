package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import edu.utsa.cs3443.botanicalbuddy.model.DestinationList;

public class MapActivity extends AppCompatActivity {

    /**
     * Holds the list of destinations loaded into the application.
     */
    DestinationList list;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

            list = new DestinationList();
        Log.i("load","load");
             try {
                list.loadDestinations(this); //load destination data
                Log.i("load","load");
            } catch (IOException e) {
                throw new RuntimeException(e); // handle exception
            }

        // **Button Click Listeners**

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MapActivity","Button1 pressed");
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 1); // passing id for destination 1
                startActivity(intent);


            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MapActivity","Button2 pressed");
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 2); // passing id for destination 2
                startActivity(intent);


            }
        });

        // **Main Logo Click Listener**
        ImageView logo = findViewById(R.id.main_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, MainActivity.class)); // log's user out
            }
        });

        /**
         * Initializes the dropdown menu to handle navigation.
         * */
        ImageView menu = findViewById(R.id.dropdown_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    /**
     * Displays and handles interactions with the dropdown menu.
     *
     * @param v The view (the menu icon) that was clicked.
     */
    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(MapActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menuMap) {
                    startActivity(new Intent(MapActivity.this, MapActivity.class));
                }
                if(item.getItemId() == R.id.menuConserve) {
                    startActivity(new Intent(MapActivity.this, ConserveActivity.class));
                }
                if(item.getItemId() == R.id.menuLogin) {
                    Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logout);
                    finish();
                }

                return true;
            }
        });
        popupMenu.show();
    }
}