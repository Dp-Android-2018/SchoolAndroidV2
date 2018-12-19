package dp.schoolandroid.application;

import dp.schoolandroid.di.component.DaggerNetworkComponent;
import dp.schoolandroid.di.component.NetworkComponent;
import dp.schoolandroid.di.modules.AppModule;

public class MyApp extends android.app.Application {

    private NetworkComponent daggerNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDaggerNetworkComponent();
    }

    public void initializeDaggerNetworkComponent() {
        daggerNetworkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public NetworkComponent getDaggerNetworkComponent() {
        return daggerNetworkComponent;
    }
}
