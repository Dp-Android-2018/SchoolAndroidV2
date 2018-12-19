package dp.schoolandroid.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dp.schoolandroid.R;
import dp.schoolandroid.di.scope.SharedPreferenceScope;

@Module
public class PreferenceEditorModule {


    @Provides
    @SharedPreferenceScope
    SharedPreferences.Editor getSharedPreferenceEditor(Context context) {
        return context.getSharedPreferences(context.getResources().getString(R.string.action_close)
                , context.MODE_PRIVATE).edit();
    }


    @Provides
    @SharedPreferenceScope
    SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(context.getResources().getString(R.string.action_close)
                , context.MODE_PRIVATE);
    }
}
