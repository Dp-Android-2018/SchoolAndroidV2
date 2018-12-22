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
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.repository.remotes.ParentLoginRepository;
import dp.schoolandroid.service.repository.remotes.StudentLoginRepository;
import retrofit2.Response;

public class StudentLoginActivityViewModel extends AndroidViewModel {
    public ObservableField<String> ssn;
    public ObservableField<String> password;
    private LiveData<Response<StudentResponse>> studentLoginResponseLiveData = new MutableLiveData<>();
    private LiveData<ForgetPasswordResponse> forgetPasswordResponseLiveData;
    private Application application;

    public StudentLoginActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        initializeVariables();
    }

    private void initializeVariables() {
        ssn = new ObservableField<>();
        ssn.set("0482539377");
        password = new ObservableField<>();
        password.set("qwe123");
    }

    public void handleloginStudent() {
        studentLoginResponseLiveData = StudentLoginRepository.getInstance().loginAsStudent(application , ssn.get(), password.get());
    }

    public void forgetPasswordConstraintLayout(View view) {
        if (ValidationUtils.validateTexts(ssn.get(), ValidationUtils.TYPE_PHONE)) {
            forgetPasswordResponseLiveData = StudentLoginRepository.getInstance().forgetPasswordStudent(application, ssn.get());
        } else {
            Toast.makeText(application, "Error Phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<Response<StudentResponse>> getStudentLoginResponseLiveData() {
        return studentLoginResponseLiveData;
    }

    public LiveData<ForgetPasswordResponse> getForgetPasswordResponseLiveData() {
        return forgetPasswordResponseLiveData;
    }
}
