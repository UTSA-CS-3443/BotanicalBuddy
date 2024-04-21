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
                list.loadDestinations(this);
                Log.i("load","load");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        ImageView logo = findViewById(R.id.main_logo);
        ImageView menu = findViewById(R.id.dropdown_menu);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 1);
                startActivity(intent);


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MapActivity.this, AttractionsPageActivity.class);
                intent.putExtra("descriptionId", 2);
                startActivity(intent);


            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, MainActivity.class));
            }
        });
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
                    startActivity(new Intent(MapActivity.this, LoginActivity.class));
                }

                return true;
            }
        });
        popupMenu.show();
    }
}