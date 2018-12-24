package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import dp.schoolandroid.application.MyApp;
import dp.schoolandroid.di.component.NetworkComponent;

public class GetApiInterfaces {
    private static GetApiInterfaces instance;

    public static GetApiInterfaces getInstance() {
        if (instance == null) {
            instance = new GetApiInterfaces();
        }
        return instance;
    }

    ApiInterfaces getApiInterfaces(Application application) {
        NetworkComponent daggerNetworkComponent = ((MyApp) application).getDaggerNetworkComponent();
        return daggerNetworkComponent.getRetrofitApiInterfaces();
    }
}
