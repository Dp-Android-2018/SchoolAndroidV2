package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import dp.schoolandroid.service.repository.remotes.NewsFeedRepository;
import dp.schoolandroid.service.repository.remotes.TeacherGetScheduleRepository;

public class BaseFragmentWithDataViewModel extends AndroidViewModel {
    private final LiveData<FeedsResponse> data;

    public BaseFragmentWithDataViewModel(@NonNull Application application) {
        super(application);
        data = NewsFeedRepository.getInstance().getNewsFeed(application);
    }

    public LiveData<FeedsResponse> getData() {
        return data;
    }
}
