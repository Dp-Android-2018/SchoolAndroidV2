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

    public void handleGetNewsFeed(String memberType){
        data = NewsFeedRepository.getInstance().getNewsFeed(application,memberType);
    }

    public LiveData<Response<FeedsResponse>> getData() {
        return data;
    }
}
