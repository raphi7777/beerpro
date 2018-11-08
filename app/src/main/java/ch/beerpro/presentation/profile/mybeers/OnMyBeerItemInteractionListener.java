package ch.beerpro.presentation.profile.mybeers;

import android.content.Context;
import android.widget.ImageView;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.profile.mywishlist.WishlistViewModel;

public interface OnMyBeerItemInteractionListener {

    void onMoreClickedListener(ImageView photo, Beer item);

    void onWishClickedListener(Beer item);

    MyBeersViewModel getModel();

    Context getContext();
}
