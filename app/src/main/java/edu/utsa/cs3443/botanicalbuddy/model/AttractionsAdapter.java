package edu.utsa.cs3443.botanicalbuddy.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import edu.utsa.cs3443.botanicalbuddy.R;

public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.ViewHolder> {

    private List<Destination> destinations;

    public AttractionsAdapter(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attraction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Destination destination = destinations.get(position);
        holder.textViewName.setText(destination.getName());
        holder.textViewDescription.setText(destination.getDescription());
        // Assuming each destination ID corresponds to a drawable ID
        int imageResId = holder.itemView.getContext().getResources().getIdentifier("destination_" + destination.getId(), "drawable", holder.itemView.getContext().getPackageName());
        holder.imageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewAttractionName);
            textViewDescription = itemView.findViewById(R.id.textViewAttractionDescription);
            imageView = itemView.findViewById(R.id.imageViewAttraction);
        }
    }
}
