package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.repository.remotes.NewsFeedRepository;
import retrofit2.Response;

public class NewsFeedFragmentViewModel extends AndroidViewModel {
    private LiveData<Response<FeedsResponse>> data;
    private Application application;

    public NewsFeedFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void handleGetTeacherNewsFeed(){
        data = NewsFeedRepository.getInstance().getTeacherNewsFeed(application);
    }

    public void handleGetStudentNewsFeed(){
        data = NewsFeedRepository.getInstance().getStudentNewsFeed(application);
    }

    public LiveData<Response<FeedsResponse>> getData() {
        return data;
    }
}
