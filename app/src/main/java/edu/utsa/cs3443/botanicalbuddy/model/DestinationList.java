package edu.utsa.cs3443.botanicalbuddy.model;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import edu.utsa.cs3443.botanicalbuddy.MapActivity;


/**
 * @author Jesus Torres
 * This class represents a destination list of Destination objects.
 * It loads destinations from destinations.csv and loads them into an array list.
 */
public class DestinationList {

    private ArrayList<Destination> destinations;


    /**
     * Initializes a new array list
     */
    public DestinationList() {
        destinations = new ArrayList<Destination>();
    }

    /**
     * Loads the list of destinations into an array list from destinations.csv
     * @param context
     * @throws IOException
     */
    public void loadDestinations(Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open("destinations.csv");
        Scanner scan = new Scanner(file);
        String line = "";
        String[] tokens;
        while(scan.hasNextLine()) {
            line = scan.nextLine();
            tokens = line.split(":");
            destinations.add(new Destination(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));
        }
    }

    /**
     * Gets the array list of destinations
     * @return ArrayList<Destination>, the array list of destination objects
     */

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

}