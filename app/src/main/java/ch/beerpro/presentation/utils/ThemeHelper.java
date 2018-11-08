package ch.beerpro.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ThemeHelper {
    public static boolean isDarkTheme(Context context) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String selectedTheme = sharedPrefs.getString("list_preference", "AppTheme");
        return !selectedTheme.equals("AppTheme");
    }
}
