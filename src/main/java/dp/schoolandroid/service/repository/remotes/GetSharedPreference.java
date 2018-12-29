package dp.schoolandroid.service.repository.remotes;

import android.app.Application;

import dp.schoolandroid.Utility.SharedPreferenceHandler;
import dp.schoolandroid.application.MyApp;
import dp.schoolandroid.di.component.NetworkComponent;

public class GetSharedPreference {
    private static GetSharedPreference instance;

    public static GetSharedPreference getInstance() {
        if (instance == null) {
            instance = new GetSharedPreference();
        }
        return instance;
    }

    public SharedPreferenceHandler getSharedPreference(Application application) {
        NetworkComponent daggerNetworkComponent = ((MyApp) application).getDaggerNetworkComponent();
        return daggerNetworkComponent.getSharedPreferenceInstance();
    }
}
