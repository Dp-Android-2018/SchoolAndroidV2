package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.StudentRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StudentLoginRepository {
    private static StudentLoginRepository instance;

    private StudentLoginRepository() {
    }

    public static StudentLoginRepository getInstance() {
        if (instance == null) {
            instance = new StudentLoginRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<StudentResponse>> loginAsStudent(final Application application, String ssn, String password) {
        final MutableLiveData<Response<StudentResponse>> data = new MutableLiveData<>();
        StudentRequest studentLoginRequest = getStudentLoginRequest(ssn, password);
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsStudent(ConfigurationFile.Constants.API_KEY,ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, studentLoginRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(studentResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(studentResponseResponse);
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordStudent(final Application application, final String phoneNumber) {

        ForgetPasswordRequest forgetPasswordRequest = getStudentPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordStudent(ConfigurationFile.Constants.API_KEY,ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, forgetPasswordRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forgetPasswordResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(forgetPasswordResponseResponse);
                });
        return data;
    }

    private ForgetPasswordRequest getStudentPasswordRequest(String phoneNumber) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setSsn(phoneNumber);
        return teacherLoginRequest;
    }

    private StudentRequest getStudentLoginRequest(String ssn, String password) {
        StudentRequest studentLoginRequest = new StudentRequest();
        studentLoginRequest.setStudentSSN(ssn);
        studentLoginRequest.setStudentPassword(password);
        return studentLoginRequest;
    }
}
