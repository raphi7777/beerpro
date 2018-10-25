package ch.beerpro.presentation.profile.myfridge;

import android.util.Pair;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.FridgeRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;

public class MyFridgeViewModel extends ViewModel implements CurrentUser {
    private static final String TAG = "MyFridgeViewModel";
    private final MutableLiveData<String> currentUserId = new MutableLiveData<>();
    private final FridgeRepository fridgeRepository;
    private final BeersRepository beersRepository;

    public MyFridgeViewModel() {
        fridgeRepository = new FridgeRepository();
        beersRepository = new BeersRepository();
        currentUserId.setValue(getCurrentUser().getUid());
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeWithBeers() {
        return fridgeRepository.getMyFridgeWithBeers(currentUserId, beersRepository.getAllBeers());
    }

    public void updateFridge(FridgeItem fridgeItem) {
        if(fridgeItem.getAmount().equals("0") || fridgeItem.getAmount().isEmpty()) {
            fridgeRepository.remove(fridgeItem);
        } else {
            fridgeRepository.setAmount(fridgeItem);
        }
    }
}