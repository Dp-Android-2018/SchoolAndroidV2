package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import dp.schoolandroid.service.repository.remotes.ParentLoginRepository;
import retrofit2.Response;

public class ParentLoginActivityViewModel extends AndroidViewModel {
    public ObservableField<String> phoneNumber;
    public ObservableField<String> password;
    private LiveData<Response<ForgetPasswordResponse>> forgetPasswordResponseLiveData;
    private LiveData<Response<ParentResponse>> parentLoginResponseLiveData = new MutableLiveData<>();
    private Application application;

    public ParentLoginActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        initializeVariables();
    }

    private void initializeVariables() {
        phoneNumber = new ObservableField<>();
        phoneNumber.set("0482539377");
        password = new ObservableField<>();
        password.set("qwe123");
    }


    public void handleloginParent() {
        parentLoginResponseLiveData = ParentLoginRepository.getInstance().loginAsParent(application, phoneNumber.get(), password.get());
    }

    public void handleForgetPasswordParent() {
        forgetPasswordResponseLiveData = ParentLoginRepository.getInstance().forgetPasswordParent(application, phoneNumber.get());
    }

    public LiveData<Response<ParentResponse>> getParentLoginResponseLiveData() {
        return parentLoginResponseLiveData;
    }

    public LiveData<Response<ForgetPasswordResponse>> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}
