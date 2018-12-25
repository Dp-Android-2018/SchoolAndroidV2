package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.repository.remotes.ForgetPasswordRepository;
import retrofit2.Response;

public class ForgetPasswordViewModel extends AndroidViewModel {
    private Application application;
    public ObservableField<String> code;
    private LiveData<Response<ForgetPasswordResponse>> forgetPasswordResponseLiveData;

    public ForgetPasswordViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        initializeVariables();
    }

    private void initializeVariables() {
        code = new ObservableField<>();
    }

    public void handleCheckCode(int membershipType,String phoneNumber){
        switch (membershipType) {
            case ConfigurationFile.Constants.TEACHER_ACTIVITY_CODE:
                forgetPasswordResponseLiveData = ForgetPasswordRepository.getInstance().generatePasswordResetTokenTeacher(application, phoneNumber, code.get());
                break;
            case ConfigurationFile.Constants.PARENT_ACTIVITY_CODE:
                forgetPasswordResponseLiveData = ForgetPasswordRepository.getInstance().generatePasswordResetTokenParent(application, phoneNumber, code.get());
                break;
            case ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE:
                forgetPasswordResponseLiveData = ForgetPasswordRepository.getInstance().generatePasswordResetTokenStudent(application, phoneNumber, code.get());
                break;
        }
    }

    public LiveData<Response<ForgetPasswordResponse>> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}

