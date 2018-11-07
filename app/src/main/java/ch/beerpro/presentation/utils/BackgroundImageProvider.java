package ch.beerpro.presentation.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ch.beerpro.R;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class BackgroundImageProvider {

    static int[] backgrounds = {R.drawable.bg_beers_card_1, R.drawable.bg_beers_card_2, R.drawable.bg_beers_card_3,
            R.drawable.bg_beers_card_4, R.drawable.bg_beers_card_5, R.drawable.bg_beers_card_6,
            R.drawable.bg_beers_card_7, R.drawable.bg_beers_card_8, R.drawable.bg_beers_card_9,
            R.drawable.bg_beers_card_10, R.drawable.bg_beers_card_11, R.drawable.bg_beers_card_12,
            R.drawable.bg_beers_card_13, R.drawable.bg_beers_card_14};

    static {
        Arrays.sort(backgrounds);
    }

    public static Task<byte[]> download(String imagename) {
        try {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference gsReference = storage.getReferenceFromUrl("gs://beerpro-3c6a5.appspot.com/" + imagename);
            final long FIVE_MEGABYTE = 5 * 1024 * 1024;
            return gsReference.getBytes(FIVE_MEGABYTE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Drawable convertToDrawable(Resources resources, byte[] image) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        Drawable drawable = new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 400, 250, true));

        return drawable;
    }

    public static Drawable getBackgroundImage(Context res, int position) {
        return res.getDrawable(backgrounds[position % backgrounds.length]);
    }
}
