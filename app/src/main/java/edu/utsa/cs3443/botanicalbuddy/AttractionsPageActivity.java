package edu.utsa.cs3443.botanicalbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

    private void initializeImageMap() {
        destinationImageMap = new HashMap<>();
        // Populate the map with destination names and corresponding image resource IDs
        destinationImageMap.put("Halsell Welcome Building", R.drawable.mays_fam_display);
        destinationImageMap.put("H-E-B Discovery Center", R.drawable.kumamoto_en);
        destinationImageMap.put("The Zachry Foundation Culinary Garden", R.drawable.greehey_lawn);
        // Add other destinations similarly...
    }
}


