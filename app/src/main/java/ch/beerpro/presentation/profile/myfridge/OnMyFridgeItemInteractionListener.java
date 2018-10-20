package ch.beerpro.presentation.profile.myfridge;

import android.widget.ImageView;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;

public interface OnMyFridgeItemInteractionListener {

    void onMoreClickedListener(ImageView photo, Beer beer);

    void onSaveClickedListener(FridgeItem fridgeItem);
}