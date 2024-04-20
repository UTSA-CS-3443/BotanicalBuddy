package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import edu.utsa.cs3443.botanicalbuddy.model.Destination;

public class AttractionsPageActivity extends AppCompatActivity {

    private Map<String, Integer> destinationImageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        ImageView logo = findViewById(R.id.main_logo);
        ImageView menu = findViewById(R.id.dropdown_menu);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AttractionsPageActivity.this, MainActivity.class));
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

        initializeImageMap();

        TextView textViewName = findViewById(R.id.textViewAttractionName);
        ImageView imageViewAttraction = findViewById(R.id.imageViewAttraction);
        TextView textViewDescription = findViewById(R.id.textViewAttractionDescription);

        Destination destination = (Destination) getIntent().getSerializableExtra("destination");

        if (destination != null) {
            textViewName.setText(destination.getName());
            textViewDescription.setText(destination.getDescription());

            Integer imageResId = destinationImageMap.getOrDefault(destination.getName(), R.drawable.main_logo);
            imageViewAttraction.setImageResource(imageResId);
        } else {
            textViewName.setText("No destination data available");
            textViewDescription.setText("Details not available.");
            imageViewAttraction.setVisibility(View.GONE);
        }
    }

    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(AttractionsPageActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menuMap) {
                    startActivity(new Intent(AttractionsPageActivity.this, MapActivity.class));
                }
                if(item.getItemId() == R.id.menuConserve) {
                    startActivity(new Intent(AttractionsPageActivity.this, ConserveActivity.class));
                }
                if(item.getItemId() == R.id.menuLogin) {
                    startActivity(new Intent(AttractionsPageActivity.this, LoginActivity.class));
                }

                return true;
            }
        });
        popupMenu.show();
    }
    private void initializeImageMap() {
        destinationImageMap = new HashMap<>();
        // Populate the map with destination names and corresponding image resource IDs
        destinationImageMap.put("Mays Family Display Garden", R.drawable.mays_fam_display);
        destinationImageMap.put("Kumamoto enr", R.drawable.kumamoto_en);
        destinationImageMap.put("greehey lawn", R.drawable.greehey_lawn);

    }
}


