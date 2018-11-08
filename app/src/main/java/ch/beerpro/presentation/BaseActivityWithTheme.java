package ch.beerpro.presentation;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ch.beerpro.R;
import ch.beerpro.presentation.utils.ThemeHelper;


public class BaseActivityWithTheme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeHelper.isDarkTheme(getBaseContext()) ? getDarkThemeId() : getLightThemeId());
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

    protected int getColorPrimary() {
        return ThemeHelper.isDarkTheme(getBaseContext()) ? R.color.colorPrimary_dark : R.color.colorPrimary;
    }

    protected int getColorPrimaryDark() {
        return ThemeHelper.isDarkTheme(getBaseContext()) ? R.color.colorPrimaryDark_dark : R.color.colorPrimaryDark;
    }

    protected int getColorAccent() {
        return ThemeHelper.isDarkTheme(getBaseContext()) ? R.color.colorAccent_dark : R.color.colorAccent;
    }

    protected int getWindowBackgroundColor() {
        return ThemeHelper.isDarkTheme(getBaseContext()) ? R.color.windowBackgroundColor_dark : R.color.windowBackgroundColor;
    }

}
