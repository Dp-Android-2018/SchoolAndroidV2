package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.repository.remotes.StudentLoginRepository;
import dp.schoolandroid.service.repository.remotes.TeacherRepository;
import retrofit2.Response;

public class EditPasswordViewModel extends AndroidViewModel {
    private LiveData<Response<ForgetPasswordResponse>> changePasswordResponseLiveData;
    private Application application;
    public ObservableField<String> oldPassword;
    public ObservableField<String> newPassword;
    public ObservableField<String> newPasswordConfirmation;

    public EditPasswordViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        initializeVariables();
    }

    private void initializeVariables() {
        oldPassword=new ObservableField<>();
        newPassword=new ObservableField<>();
        newPasswordConfirmation=new ObservableField<>();
    }

    public void handleChangePasswordTeacher(){
        changePasswordResponseLiveData=TeacherRepository.getInstance().changePasswordTeacher(application,oldPassword.get(),newPassword.get(),newPasswordConfirmation.get());
    }

    public void handleChangePasswordStudent(){
        changePasswordResponseLiveData=StudentLoginRepository.getInstance().changePasswordStudent(application,oldPassword.get(),newPassword.get(),newPasswordConfirmation.get());
    }

    public LiveData<Response<ForgetPasswordResponse>> getChangePasswordResponseLiveData() {
        return changePasswordResponseLiveData;
    }
}
