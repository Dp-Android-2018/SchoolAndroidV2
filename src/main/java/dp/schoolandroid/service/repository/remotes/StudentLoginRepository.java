package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.StudentRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import retrofit2.Call;
import retrofit2.Callback;
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

    public LiveData<Response<StudentResponse>> loginAsStudent(final Application application, String ssn, String password) {
        final MutableLiveData<Response<StudentResponse>> data = new MutableLiveData<>();
        StudentRequest studentLoginRequest = getStudentLoginRequest(ssn, password);
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsStudent(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, studentLoginRequest).enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(@NonNull Call<StudentResponse> call, @NonNull Response<StudentResponse> response) {
                data.setValue(response);
            }

            @Override
            public void onFailure(@NonNull Call<StudentResponse> call, @NonNull Throwable t) {
            }
        });
        return data;
    }

    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordStudent(final Application application, final String phoneNumber) {

        ForgetPasswordRequest forgetPasswordRequest = getStudentPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordStudent(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                data.setValue(response);
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
            }
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
        studentLoginRequest.setSsn(ssn);
        studentLoginRequest.setPassword(password);
        return studentLoginRequest;
    }
}
