package dp.schoolandroid.di.component;

import dagger.Component;
import dp.schoolandroid.view.ui.activity.HomeActivity;

@Component
public interface FragmentComponent {

    void inject(HomeActivity homeActivity);

}
