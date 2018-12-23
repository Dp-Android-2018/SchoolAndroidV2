package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordRepository {
    private static ForgetPasswordRepository instance;

    public static ForgetPasswordRepository getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordRepository();
        }
        return instance;
    }

    public LiveData<Response<ForgetPasswordResponse>> generatePasswordResetTokenTeacher(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber, code);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenTeacher(ConfigurationFile.Constants.CONTENT_TYPE,
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

    public LiveData<Response<ForgetPasswordResponse>> generatePasswordResetTokenParent(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber, code);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenParent(ConfigurationFile.Constants.CONTENT_TYPE,
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

    public LiveData<Response<ForgetPasswordResponse>> generatePasswordResetTokenStudent(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getStudentPasswordRequest(phoneNumber, code);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenStudent(ConfigurationFile.Constants.CONTENT_TYPE,
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

    private ForgetPasswordRequest getTeacherPasswordRequest(String phoneNumber, String code) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        teacherLoginRequest.setCode(code);
        return teacherLoginRequest;
    }

    private ForgetPasswordRequest getStudentPasswordRequest(String phoneNumber, String code) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setSsn(phoneNumber);
        teacherLoginRequest.setCode(code);
        return teacherLoginRequest;
    }
}

