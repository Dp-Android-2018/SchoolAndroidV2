package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.TeacherRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLoginRepository {
    private static TeacherLoginRepository instance;

    private TeacherLoginRepository() {
    }

    public static TeacherLoginRepository getInstance() {
        if (instance == null) {
            instance = new TeacherLoginRepository();
        }
        return instance;
    }

    public LiveData<Response<TeacherResponse>> loginAsTeacher(final Application application, String phoneNumber, String password) {

        TeacherRequest teacherLoginRequest = getTeacherLoginRequest(phoneNumber, password);
        final MutableLiveData<Response<TeacherResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsTeacher(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, teacherLoginRequest).enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeacherResponse> call, @NonNull Response<TeacherResponse> response) {
                data.setValue(response);
            }

            @Override
            public void onFailure(@NonNull Call<TeacherResponse> call, @NonNull Throwable t) {
            }
        });
        return data;
    }

    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordTeacher(final Application application, final String phoneNumber) {

        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordTeacher(ConfigurationFile.Constants.CONTENT_TYPE,
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

    private ForgetPasswordRequest getTeacherPasswordRequest(String phoneNumber) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        return teacherLoginRequest;
    }


    private TeacherRequest getTeacherLoginRequest(String phoneNumber, String password) {
        TeacherRequest teacherLoginRequest = new TeacherRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        teacherLoginRequest.setPassword(password);
        return teacherLoginRequest;
    }

}
