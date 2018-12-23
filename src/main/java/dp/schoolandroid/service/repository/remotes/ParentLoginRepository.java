package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.ParentRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentLoginRepository {
    private static ParentLoginRepository instance;

    private ParentLoginRepository() {
    }

    public static ParentLoginRepository getInstance() {
        if (instance == null) {
            instance = new ParentLoginRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ParentResponse>> loginAsParent(final Application application, String phone, String password) {
        final MutableLiveData<Response<ParentResponse>> data = new MutableLiveData<>();
        ParentRequest parentLoginRequest = getParenttLoginRequest(phone, password);
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsParent(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, parentLoginRequest).enqueue(new Callback<ParentResponse>() {
            @Override
            public void onResponse(@NonNull Call<ParentResponse> call, @NonNull Response<ParentResponse> response) {
                data.setValue(response);
            }

            @Override
            public void onFailure(@NonNull Call<ParentResponse> call, @NonNull Throwable t) {
            }
        });
        return data;
    }

    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordParent(final Application application, final String phoneNumber) {
        ForgetPasswordRequest forgetPasswordRequest = getParentPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordParent(ConfigurationFile.Constants.CONTENT_TYPE,
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

    private ForgetPasswordRequest getParentPasswordRequest(String phoneNumber) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        return teacherLoginRequest;
    }

    private ParentRequest getParenttLoginRequest(String phone, String password) {
        ParentRequest parenttLoginRequest = new ParentRequest();
        parenttLoginRequest.setPhone(phone);
        parenttLoginRequest.setPassword(password);
        return parenttLoginRequest;
    }
}
