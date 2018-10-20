package ch.beerpro.domain.models;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;

import javax.annotation.Nonnull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@IgnoreExtraProperties
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FridgeItem implements Entity {
    public static final String COLLECTION = "fridge";
    public static final String FIELD_USER_ID = "userId";
    public static final String FIELD_ADDED_AT = "addedAt";
    public static final String FIELD_AMOUNT = "amount";
    @Exclude
    private String id;
    @NonNull
    private String userId;
    @NonNull
    private String amount;
    @Nonnull
    private String beerId;
    private Date addedAt = new Date();
    public static String generateId(String userId, String beerId) {
        return String.format("%s_%s", userId, beerId);
    }
}
