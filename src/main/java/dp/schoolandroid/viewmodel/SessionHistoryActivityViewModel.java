package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.teacherresponse.SessionHistoryResponse;
import dp.schoolandroid.service.repository.remotes.SessionHistoryRepository;
import retrofit2.Response;

public class SessionHistoryActivityViewModel extends AndroidViewModel {
    private Application application;
    private LiveData<Response<SessionHistoryResponse>> data;

    public SessionHistoryActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void executeGetSessionsForSession(int sessionId){
        data=SessionHistoryRepository.getInstance().getAllSessionsForSession(application,sessionId);
    }

    public LiveData<Response<SessionHistoryResponse>> getData() {
        return data;
    }
}
