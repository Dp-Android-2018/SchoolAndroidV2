package dp.schoolandroid.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dp.schoolandroid.Utility.SharedPreferenceHandler;

@Module
public class SharedPreferenceModule {

    @Singleton
    @Provides
    SharedPreferenceHandler getSharedPreferenceUtils(Context context) {
        return new SharedPreferenceHandler(context);
    }
}
