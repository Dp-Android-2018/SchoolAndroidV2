package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import dp.schoolandroid.application.MyApp;
import dp.schoolandroid.di.component.NetworkComponent;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedRepository {
    private static NewsFeedRepository instance;
    private String bearerToken;
    private NewsFeedRepository(){}

    public static NewsFeedRepository getInstance() {
        if (instance == null) {
            instance = new NewsFeedRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<FeedsResponse> getNewsFeed(final Application application) {
        final MutableLiveData<FeedsResponse> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getNewsFeed(bearerToken,
                "application/json", "application/json").enqueue(new Callback<FeedsResponse>() {
            @Override
            public void onResponse(@NonNull Call<FeedsResponse> call, @NonNull Response<FeedsResponse> response) {
                if (response.code() ==200){
                    data.setValue(response.body());
                }else {
                    Toast.makeText(application, "Login code: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FeedsResponse> call, @NonNull Throwable t) {
                Toast.makeText(application, "Login code: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }


}
