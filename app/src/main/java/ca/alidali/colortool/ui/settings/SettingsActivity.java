package ca.alidali.colortool.ui.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.alidali.colortool.R;

/**
 * @author Ali Dali
 * @since 13-08-2020
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.settings_activity, new SettingsFragment())
                    .commitNow();
        }
    }
}
