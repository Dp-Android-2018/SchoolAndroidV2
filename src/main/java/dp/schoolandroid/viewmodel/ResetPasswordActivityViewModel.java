package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.repository.remotes.ForgetPasswordRepository;
import dp.schoolandroid.service.repository.remotes.ResetPasswordRepository;
import retrofit2.Response;

public class ResetPasswordActivityViewModel extends AndroidViewModel {
    public ObservableField<String> password;
    public ObservableField<String> passwordConfirmation;
    private Application application;
    private LiveData<Response<ForgetPasswordResponse>> resetPasswordResponseLiveData;

    public ResetPasswordActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        initializeUi();
    }

    private void initializeUi() {
        password=new ObservableField<>();
        passwordConfirmation=new ObservableField<>();
    }

    public void handleConfirmPassword(int membershipType,String apiToken,String phoneNumber){
        switch (membershipType){
            case ConfigurationFile.Constants.TEACHER_ACTIVITY_CODE:
                resetPasswordResponseLiveData = ResetPasswordRepository.getInstance().resetPasswordTeacher(application
                        , apiToken,phoneNumber,password.get(),passwordConfirmation.get());
                break;
            case ConfigurationFile.Constants.PARENT_ACTIVITY_CODE:
                resetPasswordResponseLiveData = ResetPasswordRepository.getInstance().resetPasswordParent(application
                        ,apiToken,phoneNumber,password.get(),passwordConfirmation.get());
                break;
            case ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE:
                resetPasswordResponseLiveData = ResetPasswordRepository.getInstance().resetPasswordStudent(application
                        , apiToken,phoneNumber,password.get(),passwordConfirmation.get());
                break;
        }
    }

    public LiveData<Response<ForgetPasswordResponse>> getResetPasswordResponseLiveData() {
        return resetPasswordResponseLiveData;
    }
}
