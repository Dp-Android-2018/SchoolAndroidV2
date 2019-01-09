package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.service.repository.remotes.AboutUsRepository;
import dp.schoolandroid.service.repository.remotes.SuggestionRepository;
import retrofit2.Response;

public class SuggestionActivityViewModel extends AndroidViewModel {
    private LiveData<Response<AboutUsResponse>> data;
    private Application application;

    public SuggestionActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void handleSuggestionRequest(String title, String describtion) {
        data = SuggestionRepository.getInstance().createNewSuggestion(application,title,describtion);
    }

    public LiveData<Response<AboutUsResponse>> getData() {
        return data;
    }
}
