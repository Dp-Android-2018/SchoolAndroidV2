package dp.schoolandroid.di.component;

import dagger.Component;
import dp.schoolandroid.viewmodel.ForgetPasswordViewModel;

@Component
public interface RepositoryComponent {

    void inject(ForgetPasswordViewModel forgetPasswordViewModel);

}
