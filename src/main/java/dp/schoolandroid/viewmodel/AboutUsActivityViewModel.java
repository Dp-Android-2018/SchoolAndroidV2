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
    private Application application;
    private LiveData<Response<AboutUsResponse>> data;

    public AboutUsActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }
   public void executeGetAboutUsInfo(String memberType){
       data = AboutUsRepository.getInstance().getAboutUsInfo(application,memberType);
   }

    public LiveData<Response<AboutUsResponse>> getData() {
        return data;
    }
}
