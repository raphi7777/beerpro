package ch.beerpro.data.repositories;
import android.util.Pair;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import androidx.lifecycle.LiveData;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Entity;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.domain.models.Rating;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.domain.utils.FirestoreQueryLiveDataArray;
import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class FridgeRepository {

    public void addOrIncrementBeer(Beer beer, String userId) {
        DocumentReference document = FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .document(FridgeItem.generateId(userId, beer.getId()));
        document.get().continueWith((task) -> {
            DocumentSnapshot snapshot = task.getResult();
            if (task.isSuccessful() && snapshot.exists()) {
                return document.update(FridgeItem.FIELD_AMOUNT, snapshot.getLong(FridgeItem.FIELD_AMOUNT) + 1);
            } else if (task.isSuccessful()) {
                return document.set(new FridgeItem(userId, "1", beer.getId()));
            } else {
                throw task.getException();
            }
        });
    }

    public LiveData<List<FridgeItem>> getMyFridgeItems(LiveData<String> currentUserId) {
        return switchMap(currentUserId, FridgeRepository::getMyFridgeItemsByUser);
    }

    public static LiveData<List<FridgeItem>> getMyFridgeItemsByUser(String userId) {
        return new FirestoreQueryLiveDataArray<>(FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .orderBy(FridgeItem.FIELD_ADDED_AT, Query.Direction.DESCENDING)
                .whereEqualTo(FridgeItem.FIELD_USER_ID, userId), FridgeItem.class);
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeWithBeers(LiveData<String> currentUserId, LiveData<List<Beer>> allBeers) {
        return map(combineLatest(getMyFridgeItems(currentUserId), map(allBeers, Entity::entitiesById)), input -> {
            List<FridgeItem> fridges = input.first;
            HashMap<String, Beer> beersById = input.second;
            ArrayList<Pair<FridgeItem, Beer>> result = new ArrayList<>();
            for (FridgeItem fridge : fridges) {
                Beer beer = beersById.get(fridge.getBeerId());
                result.add(Pair.create(fridge, beer));
            }
            return result;
        });
    }

    public void remove(FridgeItem fridgeItem) {
        DocumentReference document = FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .document(fridgeItem.getId());

        document.delete();
    }

    public void setAmount(FridgeItem fridgeItem) {
        DocumentReference document = FirebaseFirestore.getInstance().collection(FridgeItem.COLLECTION)
                .document(fridgeItem.getId());
        document.get().continueWith((task) -> {
            DocumentSnapshot snapshot = task.getResult();
            if (task.isSuccessful() && snapshot.exists()) {
                return document.update(FridgeItem.FIELD_AMOUNT, fridgeItem.getAmount());
            } else {
                throw task.getException();
            }
        });
    }
}