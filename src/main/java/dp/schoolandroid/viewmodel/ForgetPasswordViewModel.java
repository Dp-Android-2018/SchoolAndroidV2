package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.di.component.DaggerFragmentComponent;
import dp.schoolandroid.di.component.DaggerRepositoryComponent;
import dp.schoolandroid.di.component.FragmentComponent;
import dp.schoolandroid.di.component.RepositoryComponent;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.repository.remotes.ForgetPasswordRepository;
import retrofit2.Response;
/*
* this class is responsible for setup Forget Password ViewModel
* checking which membership type is reseting it's password
* if teacher : generate Password Reset Token Teacher
* if student : generate Password Reset Token student
* if parent : generate Password Reset Token parent
* */
public class ForgetPasswordViewModel extends AndroidViewModel {
    private Application application;
    public ObservableField<String> code;
    private LiveData<Response<ForgetPasswordResponse>> forgetPasswordResponseLiveData;

    @Inject ForgetPasswordRepository forgetPasswordRepository;

    public ForgetPasswordViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        initializeVariables();
    }

    private void initializeVariables() {
        code = new ObservableField<>();
        setupDaggerRepositoryComponent();
    }

    private void setupDaggerRepositoryComponent() {
        RepositoryComponent component=DaggerRepositoryComponent.create();
        component.inject(this);
    }

    public void handleCheckCode(int membershipType,String phoneNumber){
        switch (membershipType) {
            case ConfigurationFile.Constants.TEACHER_ACTIVITY_CODE:
                forgetPasswordResponseLiveData = forgetPasswordRepository.generatePasswordResetTokenTeacher(application, phoneNumber, code.get());
                break;
            case ConfigurationFile.Constants.PARENT_ACTIVITY_CODE:
                forgetPasswordResponseLiveData = forgetPasswordRepository.generatePasswordResetTokenParent(application, phoneNumber, code.get());
                break;
            case ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE:
                forgetPasswordResponseLiveData = forgetPasswordRepository.generatePasswordResetTokenStudent(application, phoneNumber, code.get());
                break;
        }
    }

    public LiveData<Response<ForgetPasswordResponse>> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}

