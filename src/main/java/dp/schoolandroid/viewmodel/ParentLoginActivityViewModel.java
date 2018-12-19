package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.repository.remotes.ParentLoginRepository;
import dp.schoolandroid.service.repository.remotes.StudentLoginRepository;
import dp.schoolandroid.service.repository.remotes.TeacherLoginRepository;

public class ParentLoginActivityViewModel extends AndroidViewModel {
    public ObservableField<String> phoneNumber;
    public ObservableField<String> password;
    private LiveData<ForgetPasswordResponse> forgetPasswordResponseLiveData;
    private LiveData<ParentResponse> parentLoginResponseLiveData = new MutableLiveData<>();
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

    public void login(View view) {
        if (ValidationUtils.validateTexts(phoneNumber.get(), ValidationUtils.TYPE_PHONE)
                && ValidationUtils.validateTexts(password.get(), ValidationUtils.TYPE_PASSWORD)) {
            parentLoginResponseLiveData = ParentLoginRepository.getInstance().loginAsParent(application, phoneNumber.get(), password.get());
        } else {
            Toast.makeText(application, "Error Phone or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void forgetPasswordConstraintLayout(View view) {
        if (ValidationUtils.validateTexts(phoneNumber.get(), ValidationUtils.TYPE_PHONE)) {
            forgetPasswordResponseLiveData = ParentLoginRepository.getInstance().forgetPasswordParent(application, phoneNumber.get());
        } else {
            Toast.makeText(application, "Error Phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<ParentResponse> getParentLoginResponseLiveData() {
        return parentLoginResponseLiveData;
    }

    public LiveData<ForgetPasswordResponse> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}
