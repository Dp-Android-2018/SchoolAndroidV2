package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import dp.schoolandroid.view.ui.activity.ForgetPasswordActivity;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.ParentRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import dp.schoolandroid.view.ui.activity.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentLoginRepository {
    private static ParentLoginRepository instance;

    private ParentLoginRepository(){}

    public static ParentLoginRepository getInstance() {
        if (instance == null) {
            instance = new ParentLoginRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<ParentResponse> loginAsParent(final Application application, String phone,String password) {
        final MutableLiveData<ParentResponse> data = new MutableLiveData<>();
        ParentRequest parentLoginRequest = getParenttLoginRequest(phone,password);
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsParent("application/json",
                "application/json", parentLoginRequest).enqueue(new Callback<ParentResponse>() {
            @Override
            public void onResponse(@NonNull Call<ParentResponse> call, @NonNull Response<ParentResponse> response) {
                if (response.code()== 200){
                    Toast.makeText(application, "Login Success", Toast.LENGTH_SHORT).show();
                    data.setValue(response.body());
                    startNewActivity(application);
                }else {
                    Toast.makeText(application, "Login code: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ParentResponse> call, @NonNull Throwable t) {
                Toast.makeText(application, "Login code: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public LiveData<ForgetPasswordResponse> forgetPasswordParent(final Application application, final String phoneNumber) {

        ForgetPasswordRequest forgetPasswordRequest = getParentPasswordRequest(phoneNumber);
        final MutableLiveData<ForgetPasswordResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordParent("application/json",
                "application/json", forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if (response.code() == 200){
                    data.setValue(response.body());
                    startPasswordActivity(application,phoneNumber);
                }else {
                    Toast.makeText(application, "Error code : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Toast.makeText(application, "Failed", Toast.LENGTH_SHORT).show();
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

    private void startPasswordActivity(Application application, String phoneNumber) {
        Toast.makeText(application, "Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(application, ForgetPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ACTIVITY_NAME", 2);
        intent.putExtra("PPNUM",phoneNumber);
        application.startActivity(intent);
    }

    private void startNewActivity(Application application){
        Intent intent=new Intent(application,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
}
