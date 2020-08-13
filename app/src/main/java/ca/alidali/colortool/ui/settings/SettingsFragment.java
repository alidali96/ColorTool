package ca.alidali.colortool.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import ca.alidali.colortool.models.Language;
import ca.alidali.colortool.R;

import static ca.alidali.colortool.models.Language.LANGUAGE;

/**
 * @author Ali Dali
 * @since 13-08-2020
 */
public class SettingsFragment extends PreferenceFragmentCompat {


    public static final String TAG = SettingsFragment.class.getSimpleName();

    SharedPreferences sharedPreferences;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        Preference language = findPreference(getString(R.string.language));
        language.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                updateLanguage(Language.valueOf(o.toString()));
                return true;
            }
        });
    }


    private void updateLanguage(Language language) {

        // Save Language
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LANGUAGE, language.getLang());
        editor.apply();

        // Restart
        getActivity().recreate();
    }


}
