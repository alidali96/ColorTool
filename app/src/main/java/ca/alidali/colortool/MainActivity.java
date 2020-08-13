package ca.alidali.colortool;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

import ca.alidali.colortool.ui.main.ColorToolFragment;

/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String LANGUAGE = "language";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        // Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        // Config Language
        configureLanguage();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ColorToolFragment.newInstance())
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
            case R.id.en_language:
                updateLanguage(Language.EN);
                return true;
            case R.id.fr_language:
                updateLanguage(Language.FR);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureLanguage() {
        // Set Language
        final Language defaultLanguage = Language.EN;    // Default
        Language displayLanguage;
        try {
            displayLanguage = Language.valueOf(sharedPreferences.getString(LANGUAGE, defaultLanguage.getLanguage()));  // Get Saved Language

        } catch (Exception error) {
            Log.e(TAG, error.toString());
            displayLanguage = defaultLanguage;
        }
        // Set Locale
        final Locale locale = new Locale(displayLanguage.getLanguage());
        Locale.setDefault(locale);

        // Set Config
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void updateLanguage(Language language) {
        // Save Language
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LANGUAGE, language.getLanguage());
        editor.apply();

        // Restart
        recreate();
    }
}
