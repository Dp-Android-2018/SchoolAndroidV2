package dp.schoolandroid.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import dp.schoolandroid.application.MyApp;
import dp.schoolandroid.di.component.SharedPreferencesSubComponent;
import dp.schoolandroid.di.modules.PreferenceEditorModule;

public class SharedPreferenceHandler {

    @Inject
    SharedPreferences.Editor editor;

    @Inject
    SharedPreferences prefs;

    public SharedPreferenceHandler(Context context) {
        SharedPreferencesSubComponent sharedPreferencesSubComponent = (((MyApp) context).getDaggerNetworkComponent()).
                getSubComponentSharedPreference(new PreferenceEditorModule());
        sharedPreferencesSubComponent.inject(this);
    }

    public void addStringToSharedPreferences(String title, String value) {
        editor.putString(title, value);
        editor.commit();
    }

    public String getStringFromSharedPreferences(String title) {
        String value = "";
        value = prefs.getString(title, "");
        return value;
    }

    public void addIntegerToSharedPreferences(String title, int value) {
        editor.putInt(title, value);
        editor.commit();
    }

    public int getIntegerFromSharedPreferences(String title) {
        int value = 0;
        value = prefs.getInt(title, 0);
        return value;
    }

    public void addBooleanToSharedPreferences(String title, Boolean value) {
        editor.putBoolean(title, value);
        editor.commit();
    }

    public Boolean getBooleanFromSharedPreferences(String title) {
        Boolean value = false;
        value = prefs.getBoolean(title, false);
        return value;
    }

    public void saveObjectToSharedPreferences(String savedObjectName, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(savedObjectName, json);
        editor.commit();
    }

    public Object getSavedObject(String savedObjectName, Class objectClass) {
        Gson gson = new Gson();
        String json = prefs.getString(savedObjectName, "");
        return gson.fromJson(json, objectClass);
    }

    public void clearToken() {
        editor.clear();
        editor.apply();
    }
}