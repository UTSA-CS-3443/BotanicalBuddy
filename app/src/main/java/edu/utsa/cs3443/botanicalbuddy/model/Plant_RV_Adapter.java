package edu.utsa.cs3443.botanicalbuddy.model;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import edu.utsa.cs3443.botanicalbuddy.LoginActivity;
import edu.utsa.cs3443.botanicalbuddy.R;
import edu.utsa.cs3443.botanicalbuddy.ConserveActivity;


/**
 * @author Paul Dutton hyf570
 * This adapter class manages the data displayed in a RecyclerView for the Botanical Buddy application's ConserveActivity.
 * It creates and binds views for each Plant object in the provided plant list.
 */
public class Plant_RV_Adapter extends RecyclerView.Adapter<Plant_RV_Adapter.MyViewHolder>{
    /**
     * The Android context used to access resources.
     */
    private final Context context;
    /**
     * The list of Plant objects to be displayed in the RecyclerView.
     */
    private final ArrayList<Plant> plants;
    /**
     * An instance of ConserveActivity.ResourceProvider used to access resources (can be improved).
     */
    private final ConserveActivity.ResourceProvider resourceProvider;

    /**
     * Creates a new Plant_RV_Adapter with the specified context and a list of Plant objects.
     *
     * @param context The Android context used to access resources.
     * @param plants The list of Plant objects to be displayed.
     */
    public Plant_RV_Adapter(Context context, ArrayList<Plant> plants) {
        this.context = context;
        this.plants = plants;
        this.resourceProvider = new ConserveActivity.ResourceProvider(context);
    }

    /**
     * Creates a new Plant_RV_Adapter with the specified context and a PlantList object.
     * The adapter retrieves the list of plants from the PlantList object.
     *
     * @param context The Android context used to access resources.
     * @param plantList The PlantList object containing the list of plants to be displayed.
     */
    public Plant_RV_Adapter(Context context, PlantList plantList) {
        this.context = context;
        this.plants = plantList.getPlants();
        this.resourceProvider = new ConserveActivity.ResourceProvider(context);
    }


    /**
     * Creates a new ViewHolder object by inflating the layout for a single plant item.
     *
     * @param parent   The ViewGroup that holds the view items.
     * @param viewType The view type for the current item (not used in this implementation).
     * @return A new MyViewHolder object.
     */
    @NonNull
    @Override
    public Plant_RV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        //view.setBackgroundColor(0);
        return new Plant_RV_Adapter.MyViewHolder(view);
    }

    /**
     * Binds data from a Plant object to the corresponding view holder at a specified position.
     * Sets the plant image, common name, latin name, and checkbox state.
     *
     * @param holder   The MyViewHolder object that holds the views for the current item.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull Plant_RV_Adapter.MyViewHolder holder, int position) {

        holder.commonName.setText(plants.get(position).getCommonName());
        holder.latinName.setText(plants.get(position).getLatinName());

        int photoId = resourceProvider.getDrawableId(plants.get(position).getPhotoName());
        //Log.d("photoId", plants.get(position).getPhotoName() + " " + photoId);
        if (photoId == 0) {
            photoId = resourceProvider.getDrawableId("main_logo_flower_only");
        }
        holder.img.setImageResource(photoId);



        int colorSoftBlue = ConserveActivity.ResourceProvider.getStaticContext().getResources().getColor(R.color.soft_blue);
        int colorPrimaryPink = ConserveActivity.ResourceProvider.getStaticContext().getResources().getColor(R.color.primary_pink);
        try {
            boolean isInGarden = getItemStatus(plants.get(position).getCommonName());
            holder.checkBox.setChecked(isInGarden);
            int color = isInGarden ? colorPrimaryPink : colorSoftBlue;
            holder.cardView.setBackgroundColor(color);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //holder.checkBox.setChecked(false);
        holder.checkBox.setOnClickListener((v) -> {

            if (position != RecyclerView.NO_POSITION){
                //Log.i("RV Adapter",String.format("clicked %d", getItemId(position)));
                try {
                    if (holder.checkBox.isChecked()){
                        holder.cardView.setBackgroundColor(colorPrimaryPink);
                    } else {
                        holder.cardView.setBackgroundColor(colorSoftBlue);
                    }
                    writeToUser(plants.get(position).getCommonName());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

    /**
     * Returns the total number of items in the adapter's data set.
     *
     * @return The number of Plant objects in the plants list.
     */
    @Override
    public int getItemCount() { return plants.size(); }

    private void writeToUser(String commonName) throws IOException {
        File accounts = new File(context.getFilesDir(), "accounts.csv");
        FileInputStream csvFile = new FileInputStream(accounts);
        String currUser = LoginActivity.getCurrentUser();
        StringBuilder tempFile = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(currUser)) {
                    if(line.contains(commonName)){
                        String tempLine = line.replace(","+commonName,"");
                        tempFile.append(tempLine);
                        tempFile.append("\n");
                    } else {
                        String tempLine = line + "," + commonName;
                        tempFile.append(tempLine);
                        tempFile.append("\n");
                    }

                } else {
                    tempFile.append(line);
                    tempFile.append("\n");
                }
            }
            FileWriter writer = new FileWriter(new File(context.getFilesDir(),"accounts.csv"));
            Log.i("FUCKIN ELL M8", String.valueOf(tempFile));
            writer.write(String.valueOf(tempFile));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param commonName of a plant to check
     * @return if it's in the current user's garden
     * @throws IOException
     */
    public boolean getItemStatus(String commonName) throws IOException {
        File accounts = new File(context.getFilesDir(), "accounts.csv");
        FileInputStream csvFile = new FileInputStream(accounts);
        String currUser = LoginActivity.getCurrentUser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(currUser)) {
                    if (line.contains(commonName)){
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * implementation of the view holder to get fields to set
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView commonName;
        TextView latinName;
        CheckBox checkBox;

        CardView cardView;

        /**
         *
         * Gets all the objects within the CardView row for access/implementation.
         * @param itemView
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.plant_photo);
            commonName = itemView.findViewById(R.id.common_name);
            latinName = itemView.findViewById(R.id.latin_name);
            checkBox = itemView.findViewById(R.id.checkBox);
            cardView = itemView.findViewById(R.id.myCardView);

        }

    }

}
