package edu.utsa.cs3443.botanicalbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import edu.utsa.cs3443.botanicalbuddy.model.DestinationList;
import edu.utsa.cs3443.botanicalbuddy.model.Destination;

public class AttractionsPageActivity extends AppCompatActivity {

    private DestinationList destinationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        //pd
        destinationList = new DestinationList();
        try {
            destinationList.loadDestinations(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //[/pd]

        if (destinationList == null) {
            throw new IllegalStateException("Destination data must be loaded before accessing this activity.");
        }

        setupUI();
    }

    private void setupUI() {
        ImageView logo = findViewById(R.id.main_logo);
        ImageView menu = findViewById(R.id.dropdown_menu);
        logo.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        menu.setOnClickListener(this::showMenu);

        TextView textViewName = findViewById(R.id.textViewAttractionName);
        ImageView imageViewAttraction = findViewById(R.id.imageViewAttraction);
        TextView textViewDescription = findViewById(R.id.textViewAttractionDescription);

        int destinationId = getIntent().getIntExtra("descriptionId", -1);
        if (destinationId != -1) {
            Destination destination = findDestinationById(destinationId);
            if (destination != null) {
                textViewName.setText(destination.getName());
                textViewDescription.setText(destination.getDescription());
                int imageResId = getResources().getIdentifier(
                        "destination_" + destinationId, "drawable", getPackageName());
                imageViewAttraction.setImageResource(imageResId);
            } else {
                handleNoDestination();
            }
        } else {
            handleNoDestination();
        }
    }

    private Destination findDestinationById(int id) {
        for (Destination dest : destinationList.getDestinations()) {
            if (dest.getId() == id) {
                return dest;
            }
        }
        return null; // Return null if not found
    }

    private void handleNoDestination() {
        TextView textViewName = findViewById(R.id.textViewAttractionName);
        TextView textViewDescription = findViewById(R.id.textViewAttractionDescription);
        ImageView imageViewAttraction = findViewById(R.id.imageViewAttraction);

        textViewName.setText("No destination data available");
        textViewDescription.setText("Details not available.");
        imageViewAttraction.setVisibility(View.GONE);
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
                    Intent logout = new Intent(AttractionsPageActivity.this, LoginActivity.class);
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


