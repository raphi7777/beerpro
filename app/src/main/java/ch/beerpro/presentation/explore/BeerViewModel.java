package ch.beerpro.presentation.explore;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.domain.models.Beer;

public class BeerViewModel extends ViewModel {

    private static final String TAG = "BeerViewModel";
    private final BeersRepository beersRepository;
    private final MutableLiveData<String> currentCategory = new MutableLiveData<>();

    public BeerViewModel() {
        beersRepository = new BeersRepository();
    }

    public LiveData<List<Beer>> getBeersByCategorie(LiveData<String> currentCategory) {
        return beersRepository.getBeerByCategory(currentCategory);
    }

    public LiveData<List<Beer>> getBeersByManufacturer(LiveData<String> currentManufacturer) {
        return beersRepository.getBeerByManufacturer(currentManufacturer);
    }
}
