package ch.beerpro.presentation.explore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;


import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.GlideApp;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import androidx.annotation.NonNull;


public class BeerRecyclerViewAdapter extends ListAdapter<Beer, BeerRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "BeerRecylerViewAdap";

    private static final DiffUtil.ItemCallback<Beer> DIFF_CALLBACK = new DiffUtil.ItemCallback<Beer>() {
        @Override
        public boolean areItemsTheSame(@NonNull Beer oldItem, @NonNull Beer newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Beer oldItem, @NonNull Beer newItem) {
            return oldItem.equals(newItem);
        }
    };


    private final OnBeerlistItemInteractionListener listener;

    public BeerRecyclerViewAdapter(OnBeerlistItemInteractionListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_beers_listentry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Beer entry = getItem(position);
        holder.bind(entry, listener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.photo)
        ImageView photo;

        @BindView(R.id.category)
        TextView category;

        @BindView(R.id.manufacturer)
        TextView manufacturer;

        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Beer item, OnBeerlistItemInteractionListener listener) {
            name.setText(item.getName());
            category.setText(item.getCategory());
            manufacturer.setText(item.getManufacturer());
            ratingBar.setNumStars(5);
            ratingBar.setRating(item.getAvgRating());
            GlideApp.with(itemView).load(item.getPhoto()).apply(new RequestOptions().override(240, 240).centerInside())
                    .into(photo);
            itemView.setOnClickListener(v -> listener.onBeerClickedListener(photo, item));
        }
    }
}
