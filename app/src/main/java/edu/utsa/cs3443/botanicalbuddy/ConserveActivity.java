package edu.utsa.cs3443.botanicalbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import edu.utsa.cs3443.botanicalbuddy.model.Plant;
import edu.utsa.cs3443.botanicalbuddy.model.PlantList;
import edu.utsa.cs3443.botanicalbuddy.model.Plant_RV_Adapter;
/**
 * This activity class represents the ConserveActivity screen in the Botanical Buddy application.
 * It allows users to view and interact with a list of plants categorized based on specific criteria.
 *
 * @author Paul Dutton hyf570
 */
public class ConserveActivity extends AppCompatActivity {
    /**
     * An ArrayList containing PlantList objects representing different plant categories (e.g., shade plants, cool tones).
     */
    private ArrayList<PlantList> garden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conserve);
        Context thisContext = this;
        garden = new ArrayList<>();
        buildGarden();

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        Plant_RV_Adapter adapter = new Plant_RV_Adapter(this, garden.get(0)); //set a default for launch


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

        //----------------------------------------------Jesus' menu
        ImageView homeLogo = findViewById(R.id.main_logo);
        ImageView menu = findViewById(R.id.dropdown_menu);
        homeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConserveActivity.this, MainActivity.class));
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
        // ---------------------------------------------[/Jesus]


        Button coolTonesButton = findViewById(R.id.cool_tones);
        Button shadePlantsButton = findViewById(R.id.shade_plants);
        Button shallowSoilsButton = findViewById(R.id.shallow_soils);


        shadePlantsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(
                        new Plant_RV_Adapter(thisContext, garden.get(0)));
            }
        });

        coolTonesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(
                        new Plant_RV_Adapter(thisContext, garden.get(1)));
            }
        });

        shallowSoilsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(
                        new Plant_RV_Adapter(thisContext, garden.get(2)));
            }
        });

    }
    /**
     * Creates PlantList objects for different plant categories and populates them with data from CSV files.
     */
    private void buildGarden() {
        String[] gardenCats = {"ShadePlants", "CoolTones", "ShallowSoils"};
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
    /**
     * Inflates and shows a popup menu with navigation options.
     *
     * @param v The view that triggered the menu display.
     */
    public void showMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(ConserveActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menuMap) {
                    startActivity(new Intent(ConserveActivity.this, MapActivity.class));
                }
                if(item.getItemId() == R.id.menuConserve) {
                    startActivity(new Intent(ConserveActivity.this, ConserveActivity.class));
                }
                if(item.getItemId() == R.id.menuLogin) {
                    startActivity(new Intent(ConserveActivity.this, LoginActivity.class));
                }

                return true;
            }
        });
        popupMenu.show();
    }
    /**
     * This utility class provides methods to access application resources within the Botanical Buddy application.
     */
    public static class ResourceProvider {
        /**
         * A static reference to the application context. This approach has limitations
         * and can potentially lead to memory leaks. TODO: Consider using a non-static approach
         * or a Dependency Injection framework for better maintainability.
         */
        private static Context context;
        /**
         * Constructor for ResourceProvider.
         *
         * @param context The context used to access application resources.
         */
        public ResourceProvider(Context context) {
            this.context = context;
        }
        public static Context getStaticContext(){return context;}


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

    /**
     * This class is a custom RecyclerView.ItemDecoration that adds spacing between items in a RecyclerView layout.
     */
    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        /**
         * The height of the vertical space to be added between items.
         */
        private final int verticalSpaceHeight;

        /**
         * The width of the horizontal space to be added between items.
         */
        private final int horizontalSpaceWidth;

        /**
         * Creates a new SpaceItemDecoration object with the specified vertical and horizontal space dimensions.
         *
         * @param verticalSpaceHeight The height of the vertical space in pixels.
         * @param horizontalSpaceWidth The width of the horizontal space in pixels.
         */
        public SpaceItemDecoration(int verticalSpaceHeight, int horizontalSpaceWidth) {
            this.verticalSpaceHeight = verticalSpaceHeight;
            this.horizontalSpaceWidth = horizontalSpaceWidth;
        }

        /**
         * This method defines the offsets for decoration around each item in the RecyclerView.
         *
         * @param outRect The Rect object to be populated with offsets.
         * @param view The view that the decoration is applied to.
         * @param parent The RecyclerView parent of the view.
         * @param state The current RecyclerView.State.
         */
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