package edu.utsa.cs3443.botanicalbuddy.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PlantList {

    private ArrayList<Plant> plants;
    private final String type;
    private final String defaultPlantLogoName = "main_logo_flower_only";

    public PlantList(String type) {
        this.type = type;
        this.plants = new ArrayList<>();
    }

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
                    Log.i("PlantList",plant.toString());
                    this.plants.add(plant);
                } else if (fields.length == 2) {
                    Plant plant = new Plant(fields[0], fields[1], defaultPlantLogoName);
                    //Log.i("PlantList",plant.toString());
                    this.plants.add(plant);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Plant> getPlants() {
        return plants;
    }

}
