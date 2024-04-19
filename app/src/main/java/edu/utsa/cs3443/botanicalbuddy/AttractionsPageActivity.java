package edu.utsa.cs3443.botanicalbuddy;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.botanicalbuddy.model.Destination;

public class AttractionsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        TextView textViewName = findViewById(R.id.textViewAttractionName);
        ImageView imageViewAttraction = findViewById(R.id.imageViewAttraction);
        TextView textViewDescription = findViewById(R.id.textViewAttractionDescription);


        Destination destination = (Destination) getIntent().getSerializableExtra("destination");

        if (destination != null) {
            textViewName.setText(destination.getName());
            textViewDescription.setText(destination.getDescription());

            imageViewAttraction.setImageResource(R.drawable.mays_fam_display);
        }
    }
}

