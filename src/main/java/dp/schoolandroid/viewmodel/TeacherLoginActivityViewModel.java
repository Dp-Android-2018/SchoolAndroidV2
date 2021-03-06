package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.repository.remotes.TeacherRepository;
import retrofit2.Response;

public class TeacherLoginActivityViewModel extends AndroidViewModel {
    public ObservableField<String> phoneNumber;
    public ObservableField<String> password;
    private LiveData<Response<TeacherResponse>> teacherLoginResponseLiveData;
    private LiveData<Response<ForgetPasswordResponse>> forgetPasswordResponseLiveData;
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

    public void handleloginTeacher() {
        teacherLoginResponseLiveData = TeacherRepository.getInstance().loginAsTeacher(application, phoneNumber.get(), password.get());
    }

    public void handleForgetPasswordTeacher() {
        forgetPasswordResponseLiveData = TeacherRepository.getInstance().forgetPasswordTeacher(application, phoneNumber.get());
    }

    public LiveData<Response<TeacherResponse>> getTeacherLoginResponseLiveData() {
        return teacherLoginResponseLiveData;
    }

    public LiveData<Response<ForgetPasswordResponse>> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}
