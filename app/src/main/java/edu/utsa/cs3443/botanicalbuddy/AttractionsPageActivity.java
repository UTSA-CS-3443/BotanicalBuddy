package edu.utsa.cs3443.botanicalbuddy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;


import edu.utsa.cs3443.botanicalbuddy.model.AttractionsAdapter;
import edu.utsa.cs3443.botanicalbuddy.model.DestinationList;



public class AttractionsPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AttractionsAdapter adapter;
    private DestinationList destinationList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        recyclerView = findViewById(R.id.recyclerViewAttractions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize DestinationList and load destinations
        destinationList = new DestinationList();
        try {
            destinationList.loadDestinations(this);
        } catch (IOException e) {
            // Proper error handling e.g., showing an error message to the user
            e.printStackTrace();
        }

        // Setup adapter with loaded destinations
        adapter = new AttractionsAdapter(destinationList.getDestinations());
        recyclerView.setAdapter(adapter);
    }
}
