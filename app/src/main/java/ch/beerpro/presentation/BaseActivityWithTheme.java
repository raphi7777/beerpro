package ch.beerpro.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ch.beerpro.R;


public class BaseActivityWithTheme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String selectedTheme = sharedPrefs.getString("list_preference", "AppTheme");
        if (selectedTheme.equals("AppTheme")) {
            setTheme(getLightThemeId());
        } else {
            //it's dark theme
            setTheme(getDarkThemeId());
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            recreate();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected int getLightThemeId() {
        return R.style.AppTheme;
    }

    protected int getDarkThemeId() {
        return R.style.AppTheme_Dark;
    }

}
