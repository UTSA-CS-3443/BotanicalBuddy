package edu.utsa.cs3443.botanicalbuddy.model;
import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import edu.utsa.cs3443.botanicalbuddy.MapActivity;

public class DestinationList {

    private ArrayList<Destination> destinations;

    public DestinationList() {
        destinations = new ArrayList<Destination>();
    }

    public void loadDestinations(MapActivity activity) throws IOException {
        AssetManager manager = activity.getAssets();
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


    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

}