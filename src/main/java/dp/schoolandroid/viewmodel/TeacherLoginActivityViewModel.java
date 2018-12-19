package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.repository.remotes.TeacherLoginRepository;

public class TeacherLoginActivityViewModel extends AndroidViewModel {
    public ObservableField<String> phoneNumber;
    public ObservableField<String> password;
    private LiveData<TeacherResponse> teacherLoginResponseLiveData;
    private LiveData<ForgetPasswordResponse> forgetPasswordResponseLiveData;
    private Application application;

    public TeacherLoginActivityViewModel(@NonNull Application application) {
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
            teacherLoginResponseLiveData = TeacherLoginRepository.getInstance().loginAsTeacher(application, phoneNumber.get(), password.get());
        } else {
            Toast.makeText(application, "Error Phone or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void forgetPasswordConstraintLayout(View view) {
        if (ValidationUtils.validateTexts(phoneNumber.get(), ValidationUtils.TYPE_PHONE)) {
            forgetPasswordResponseLiveData = TeacherLoginRepository.getInstance().forgetPasswordTeacher(application, phoneNumber.get());
        } else {
            Toast.makeText(application, "Error Phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<TeacherResponse> getTeacherLoginResponseLiveData() {
        return teacherLoginResponseLiveData;
    }

    public LiveData<ForgetPasswordResponse> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}
