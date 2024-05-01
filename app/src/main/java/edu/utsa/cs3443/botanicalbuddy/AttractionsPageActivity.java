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

import edu.utsa.cs3443.botanicalbuddy.model.DestinationList;
import edu.utsa.cs3443.botanicalbuddy.model.Destination;
/**
 * Activity for displaying attractions from the map.
 * This activity shows details of a specific attraction based on the destination ID passed through intent.
 * It handles UI interactions and navigation to other activities based on user interaction.
 */
public class AttractionsPageActivity extends AppCompatActivity {

    private DestinationList destinationList;
    /**
     * Activity for displaying attractions in a tourist app.
     * This activity shows details of a specific attraction based on the destination ID passed through intent.
     * It handles UI interactions and navigation to other activities based on user interaction.
     */
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
    /**
     * Sets up the user interface by initializing UI components and setting event handlers.
     * It also configures the UI based on the destination ID passed to the activity.
     */

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
    /**
     * Finds a destination by its ID.
     * @param id The ID of the destination to find.
     * @return The Destination object if found, otherwise null.
     */

    private Destination findDestinationById(int id) {
        for (Destination dest : destinationList.getDestinations()) {
            if (dest.getId() == id) {
                return dest;
            }
        }
        return null; // Return null if not found
    }
    /**
     * Handles scenarios where no valid destination is found. This method updates the UI
     * to indicate that no data is available.
     */
    private void handleNoDestination() {
        TextView textViewName = findViewById(R.id.textViewAttractionName);
        TextView textViewDescription = findViewById(R.id.textViewAttractionDescription);
        ImageView imageViewAttraction = findViewById(R.id.imageViewAttraction);

        textViewName.setText("No destination data available");
        textViewDescription.setText("Details not available.");
        imageViewAttraction.setVisibility(View.GONE);
    }

    /**
     * Displays a popup menu when the menu icon is clicked.
     * Handles navigation based on the user's menu selection
     * @param v The view (menu icon) that was clicked.
     */
    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(AttractionsPageActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.menuMap) {
                startActivity(new Intent(AttractionsPageActivity.this, MapActivity.class));
            }
            if(item.getItemId() == R.id.menuConserve) {
                startActivity(new Intent(AttractionsPageActivity.this, ConserveLandingActivity.class));
            }
            if(item.getItemId() == R.id.menuLogin) {
                Intent logout = new Intent(AttractionsPageActivity.this, LoginActivity.class);
                finishAffinity();
                startActivity(logout);
                finish();
            }
            return true;
        });
        popupMenu.show();
    }

}


