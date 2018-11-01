package ch.beerpro.presentation.settings;

import android.os.Bundle;
import ch.beerpro.presentation.BaseActivityWithTheme;
import ch.beerpro.presentation.utils.SettingsFragment;

public class ThemePreferencesActivity extends BaseActivityWithTheme {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }


}
