package edu.utsa.cs3443.botanicalbuddy.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Paul Dutton hyf570
 * This class represents a list of plants for the Botanical Buddy application.
 * It manages loading plant data from a file stored in the application's assets folder.
 */
public class PlantList {

    /**
     * The list of plants in this PlantList object.
     */
    private ArrayList<Plant> plants;

    /**
     * The type of plant list (e.g., "flowering", "succulents", etc.).
     */
    private final String type;
    /**
     * The default filename for the plant logo image used if no logo is specified in the data file.
     */
    private final String defaultPlantLogoName = "main_logo_flower_only";

    /**
     * Creates a new PlantList object with the specified type.
     *
     * @param type The type of plant list (e.g., "flowering", "succulents", etc.).
     */
    public PlantList(String type) {
        this.type = type;
        this.plants = new ArrayList<>();
    }

    /**
     * Builds the plant list by reading data from a file stored in the application's assets folder.
     *
     * @param context  The Android context used to access the asset manager.
     * @param filename The name of the file containing plant data (located in the assets folder).
     * @throws IOException If there is an error reading the file or creating the Plant objects.
     */
    public void buildFromFile(Context context, String filename) throws IOException {
        AssetManager assMan = context.getAssets();
        Scanner scanner;
        try{
            InputStream reader = assMan.open(filename);
            scanner = new Scanner(reader);
            while(scanner.hasNextLine()){
                String[] fields = scanner.nextLine().split(",");
                if(fields.length == 3){
                    if(fields[2].isEmpty() ){
                        fields[2] = defaultPlantLogoName;
                    }
                    Plant plant = new Plant(fields[0], fields[1], fields[2]);
                    //Log.i("PlantList",plant.toString());
                    this.plants.add(plant);
                } else if (fields.length == 2) {
                    Plant plant = new Plant(fields[0], fields[1], defaultPlantLogoName);
                    //Log.i("PlantList",plant.toString());
                    this.plants.add(plant);
                } else {
                    // Handle case where data line has unexpected number of fields (optional)
                    Log.w("PlantList", "Invalid data line format in plant data file: " + scanner.nextLine());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the list of plants in this PlantList object.
     *
     * @return The ArrayList containing the Plant objects.
     */
    public ArrayList<Plant> getPlants() {
        return plants;
    }

}
