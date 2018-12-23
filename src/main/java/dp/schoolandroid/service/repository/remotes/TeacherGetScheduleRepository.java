package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherGetScheduleRepository {
    private static TeacherGetScheduleRepository instance;
    private String bearerToken;
    private TeacherGetScheduleRepository(){}

    public static TeacherGetScheduleRepository getInstance() {
        if (instance == null) {
            instance = new TeacherGetScheduleRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<TeacherScheduleResponse>> getTeacherSchedule(final Application application) {
        final MutableLiveData<Response<TeacherScheduleResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getTeacherSchedule(bearerToken,
                ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.ACCEPT).enqueue(new Callback<TeacherScheduleResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeacherScheduleResponse> call, @NonNull Response<TeacherScheduleResponse> response) {
                data.setValue(response);
            }

            @Override
            public void onFailure(@NonNull Call<TeacherScheduleResponse> call, @NonNull Throwable t) {

            }
        });
        return data;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = "Bearer "+bearerToken;
    }
}
