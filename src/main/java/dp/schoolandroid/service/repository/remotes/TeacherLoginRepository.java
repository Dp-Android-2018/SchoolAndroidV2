package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import dp.schoolandroid.view.ui.activity.ForgetPasswordActivity;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.TeacherRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.view.ui.activity.HomeActivity;
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

    public LiveData<TeacherResponse> loginAsTeacher(final Application application, String phoneNumber, String password) {

        TeacherRequest teacherLoginRequest = getTeacherLoginRequest(phoneNumber, password);
        final MutableLiveData<TeacherResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsTeacher("application/json",
                "application/json", teacherLoginRequest).enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeacherResponse> call, @NonNull Response<TeacherResponse> response) {

                if (response.code() == 200) {
                    Toast.makeText(application, "Login Success", Toast.LENGTH_SHORT).show();
                    data.setValue(response.body());
                    startHomeActivity(application);
                    if (response.body() != null) {
                        TeacherGetScheduleRepository.getInstance().setBearerToken("Bearer " + response.body().getTeacherData().getApiToken());
                        NewsFeedRepository.getInstance().setBearerToken("Bearer " + response.body().getTeacherData().getApiToken());
                    }
                } else {
                    Toast.makeText(application, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TeacherResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ForgetPasswordResponse> forgetPasswordTeacher(final Application application, final String phoneNumber) {

        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber);
        final MutableLiveData<ForgetPasswordResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordTeacher("application/json",
                "application/json", forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                    startPasswordActivity(application, phoneNumber);
                } else {
                    Toast.makeText(application, "Error code : " + response.code(), Toast.LENGTH_SHORT).show();
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

    private void startHomeActivity(Application application) {
        Intent intent = new Intent(application, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }

    private void startPasswordActivity(Application application, String phoneNumber) {
        Toast.makeText(application, "Done", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(application, ForgetPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ACTIVITY_NAME", 1);
        intent.putExtra("TPNUM", phoneNumber);
        application.startActivity(intent);
    }
}
