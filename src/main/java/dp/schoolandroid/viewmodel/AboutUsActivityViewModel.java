package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.service.model.response.ContactUsResponse;
import dp.schoolandroid.service.repository.remotes.AboutUsRepository;
import dp.schoolandroid.service.repository.remotes.ContactUsRepository;
import retrofit2.Response;

public class AboutUsActivityViewModel extends AndroidViewModel {
    private final LiveData<Response<AboutUsResponse>> data;

    public AboutUsActivityViewModel(@NonNull Application application) {
        super(application);
        data = AboutUsRepository.getInstance().getAboutUsInfo(application);
    }

    public LiveData<Response<AboutUsResponse>> getData() {
        return data;
    }
}
