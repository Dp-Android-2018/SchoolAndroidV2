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
import dp.schoolandroid.service.repository.remotes.ForgetPasswordRepository;

public class ForgetPasswordViewModel extends AndroidViewModel {
    private Application application;
    private int type;
    private String phoneNumber;
    public ObservableField<String> code;
    private LiveData<ForgetPasswordResponse> forgetPasswordResponseLiveData;

    public ForgetPasswordViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        initializeVariables();
    }

    private void initializeVariables() {
        code = new ObservableField<>();
    }

    public void check(View view) {
        if (ValidationUtils.validateTexts(code.get(), ValidationUtils.TYPE_PHONE)) {
            switch (type) {
                case 1:
                    forgetPasswordResponseLiveData = ForgetPasswordRepository.getInstance().generatePasswordResetTokenTeacher(application, phoneNumber, code.get());
                    break;
                case 2:
                    forgetPasswordResponseLiveData = ForgetPasswordRepository.getInstance().generatePasswordResetTokenParent(application, phoneNumber, code.get());
                    break;
                case 3:
                    forgetPasswordResponseLiveData = ForgetPasswordRepository.getInstance().generatePasswordResetTokenStudent(application, phoneNumber, code.get());
                    break;
            }
        } else {
            Toast.makeText(application, "Error Phone or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<ForgetPasswordResponse> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setType(int type) {
        this.type = type;
    }
}
