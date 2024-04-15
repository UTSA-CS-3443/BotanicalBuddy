package edu.utsa.cs3443.botanicalbuddy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import edu.utsa.cs3443.botanicalbuddy.model.Plant;
import edu.utsa.cs3443.botanicalbuddy.model.PlantList;
import edu.utsa.cs3443.botanicalbuddy.model.Plant_RV_Adapter;

public class ConserveActivity extends AppCompatActivity {

    private ArrayList<PlantList> garden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conserve);

        garden = new ArrayList<>();
        buildGarden();

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        Plant_RV_Adapter adapter = new Plant_RV_Adapter(this, garden.get(0));
        int verticalSpaceHeight = 50; // Example vertical spacing in pixels
        int horizontalSpaceWidth = 20; // Example horizontal spacing in pixels
        recyclerView.addItemDecoration(new SpaceItemDecoration(verticalSpaceHeight, horizontalSpaceWidth));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void buildGarden() {
        String[] gardenCats = {"CoolTones", "ShadePlants", "ShallowSoils"};
        for (String cat : gardenCats) {
            PlantList plantList = new PlantList(cat);
            String filename = cat + ".csv";
            try {
                plantList.buildFromFile(this, filename);
            } catch (IOException e) {
                Log.e("ConserveActivity", "buildGarden failed: " + cat);
                e.printStackTrace();
            }
            garden.add(plantList);

        }
    }
    public static class ResourceProvider {
        private final Context context;
        /**
         * Constructor for ResourceProvider.
         *
         * @param context The context used to access application resources.
         */
        public ResourceProvider(Context context) {
            this.context = context;
        }
        /**
         * Retrieves the drawable resource ID for a given resource name.
         *
         * @param name The name of the resource.
         * @return The drawable resource ID associated with the name.
         */
        public int getDrawableId(String name) {
            Resources resources = context.getResources();
            String packageName = context.getPackageName();
            //Log.i("ResourceProvider", "Getting drawable id for " + name +" in package " + packageName);
            //name = "optional_images/" + name;
            return resources.getIdentifier(name, "drawable", packageName);
        }
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int verticalSpaceHeight;
        private final int horizontalSpaceWidth;

        public SpaceItemDecoration(int verticalSpaceHeight, int horizontalSpaceWidth) {
            this.verticalSpaceHeight = verticalSpaceHeight;
            this.horizontalSpaceWidth = horizontalSpaceWidth;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = horizontalSpaceWidth;
            outRect.right = horizontalSpaceWidth;
            outRect.bottom = verticalSpaceHeight;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = verticalSpaceHeight;
            }
        }
    }
}