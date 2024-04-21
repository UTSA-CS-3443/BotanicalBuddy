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

import java.util.ArrayList;

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
        holder.checkBox.setChecked(false);

    }

    /**
     * Returns the total number of items in the adapter's data set.
     *
     * @return The number of Plant objects in the plants list.
     */
    @Override
    public int getItemCount() {
        return plants.size();
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

            checkBox.setOnClickListener((v) -> {
                //Log.i("RV Adapter","checkbox clicked");
                int pos = getAdapterPosition();
                int color = ConserveActivity.ResourceProvider.getStaticContext().getResources().getColor(R.color.secondary_green);
                if (pos != RecyclerView.NO_POSITION){
                    //Log.i("RV Adapter","attempting color change");
                    if(checkBox.isChecked()){
                        color = ConserveActivity.ResourceProvider.getStaticContext().getResources().getColor(R.color.primary_pink);
                        cardView.setBackgroundColor(color);
                    } else {
                        cardView.setBackgroundColor(color);
                    }
                }
                writeToUser();
            });

        }
        private void writeToUser(){
            Log.i("RV Adapter","TODO, WRITE TO FILE");
        }
    }
}
