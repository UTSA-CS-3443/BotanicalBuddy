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

public class Plant_RV_Adapter extends RecyclerView.Adapter<Plant_RV_Adapter.MyViewHolder>{
    private final Context context;
    private final ArrayList<Plant> plants;
    private final ConserveActivity.ResourceProvider resourceProvider;


    public Plant_RV_Adapter(Context context, ArrayList<Plant> plants) {
        this.context = context;
        this.plants = plants;
        this.resourceProvider = new ConserveActivity.ResourceProvider(context);

    }
    public Plant_RV_Adapter(Context context, PlantList plantList) {
        this.context = context;
        this.plants = plantList.getPlants();
        this.resourceProvider = new ConserveActivity.ResourceProvider(context);
    }

    @NonNull
    @Override
    public Plant_RV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new Plant_RV_Adapter.MyViewHolder(view);
    }

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
