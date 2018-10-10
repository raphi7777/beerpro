package ch.beerpro.presentation.explore;

import android.widget.ImageView;

import ch.beerpro.domain.models.Beer;

public interface OnBeerlistItemInteractionListener {
    void onBeerClickedListener(ImageView photo, Beer item);
}
