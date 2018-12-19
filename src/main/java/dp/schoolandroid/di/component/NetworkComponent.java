package dp.schoolandroid.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dp.schoolandroid.Utility.SharedPreferenceHandler;
import dp.schoolandroid.di.modules.AppModule;
import dp.schoolandroid.di.modules.NetworkModule;
import dp.schoolandroid.di.modules.PreferenceEditorModule;
import dp.schoolandroid.di.modules.SharedPreferenceModule;
import dp.schoolandroid.service.repository.remotes.ApiInterfaces;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, SharedPreferenceModule.class})
public interface NetworkComponent {

    ApiInterfaces getRetrofitApiInterfaces();

    SharedPreferenceHandler getSharedPreferenceInstance();

    SharedPreferencesSubComponent getSubComponentSharedPreference(PreferenceEditorModule preferenceEditorModule);
}
