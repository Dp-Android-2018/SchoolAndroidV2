package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import dp.schoolandroid.view.ui.activity.ResetPasswordActivity;
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

    public LiveData<ForgetPasswordResponse> generatePasswordResetTokenTeacher(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber, code);
        final MutableLiveData<ForgetPasswordResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenTeacher("application/json",
                "application/json", forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                    if (response.body() != null) {
                        Toast.makeText(application, "Response: " + response.body(), Toast.LENGTH_SHORT).show();
                        startResetPasswordActivity(application, response.body().getToken(), 1);
                    }
                } else {
                    Toast.makeText(application, "Error code : " + response.body(), Toast.LENGTH_SHORT).show();
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

    public LiveData<ForgetPasswordResponse> generatePasswordResetTokenParent(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber, code);
        final MutableLiveData<ForgetPasswordResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenParent("application/json",
                "application/json", forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                    if (response.body() != null) {
                        Toast.makeText(application, "Response: " + response.body(), Toast.LENGTH_SHORT).show();
                        startResetPasswordActivity(application, response.body().getToken(), 2);
                    }
                } else {
                    Toast.makeText(application, "Error code : " + response.body(), Toast.LENGTH_SHORT).show();
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

    public LiveData<ForgetPasswordResponse> generatePasswordResetTokenStudent(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getStudentPasswordRequest(phoneNumber, code);
        final MutableLiveData<ForgetPasswordResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenStudent("application/json",
                "application/json", forgetPasswordRequest).enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                    if (response.body() != null) {
                        Toast.makeText(application, "Response: " + response.body(), Toast.LENGTH_SHORT).show();
                        startResetPasswordActivity(application, response.body().getMessage(), 3);
                    }
                } else {
                    Toast.makeText(application, "Error : " + response.code(), Toast.LENGTH_SHORT).show();
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

    private void startResetPasswordActivity(Application application, String apiToken, int type) {
        Intent intent = new Intent(application, ResetPasswordActivity.class);
        intent.putExtra("APITOKEN", apiToken);
        intent.putExtra("TYPE", type);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
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
