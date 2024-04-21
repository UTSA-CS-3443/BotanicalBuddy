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

public class ConserveActivity extends AppCompatActivity {

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

        //Jesus' menu
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
        // [/Jesus]













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
    public static class ResourceProvider {
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