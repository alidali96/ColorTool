package ca.alidali.colortool;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import ca.alidali.colortool.models.Language;
import ca.alidali.colortool.ui.color_tool.ColorToolFragment;
import ca.alidali.colortool.ui.settings.SettingsActivity;

import static ca.alidali.colortool.models.Language.LANGUAGE;

/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        // Config Language
        configureLanguage();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity, ColorToolFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lang_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureLanguage() {
        // Set Language
        final Language defaultLanguage = Language.EN;    // Default
        Language displayLanguage;
        try {
            displayLanguage = Language.valueOf(sharedPreferences.getString(LANGUAGE, defaultLanguage.getLang()));  // Get Saved Language
        } catch (Exception error) {
            Log.e(TAG, error.toString());
            displayLanguage = defaultLanguage;
        }
        // Set Locale
        final Locale locale = new Locale(displayLanguage.getLang());
        Locale.setDefault(locale);

        // Set Config
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(LANGUAGE)) {
            configureLanguage();
            recreate();
        }
    }
}
