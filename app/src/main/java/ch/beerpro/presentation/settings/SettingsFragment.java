package ch.beerpro.presentation.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import androidx.annotation.Nullable;
import ch.beerpro.R;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (getActivity() != null) {
            getActivity().recreate();
            String selectedTheme = sharedPreferences.getString(key, "AppTheme");
            if (selectedTheme.equals("AppTheme")) {
                getActivity().getApplication().setTheme(R.style.AppTheme);
            } else {
                getActivity().getApplication().setTheme(R.style.AppTheme_Dark);

            }

        }
    }


}
