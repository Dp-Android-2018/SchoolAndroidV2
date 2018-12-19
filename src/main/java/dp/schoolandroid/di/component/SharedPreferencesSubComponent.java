package dp.schoolandroid.di.component;

import dagger.Subcomponent;
import dp.schoolandroid.Utility.SharedPreferenceHandler;
import dp.schoolandroid.di.modules.PreferenceEditorModule;
import dp.schoolandroid.di.scope.SharedPreferenceScope;

@SharedPreferenceScope
@Subcomponent(modules = {PreferenceEditorModule.class})
public interface SharedPreferencesSubComponent {

    void inject(SharedPreferenceHandler sharedPreferenceHandler);
}
