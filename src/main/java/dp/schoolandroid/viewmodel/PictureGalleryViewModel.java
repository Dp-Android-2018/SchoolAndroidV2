package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.service.model.response.GalleryResponse;
import dp.schoolandroid.service.repository.remotes.PictureGalleryRepository;
import retrofit2.Response;

public class PictureGalleryViewModel extends AndroidViewModel {
    private LiveData<Response<GalleryResponse>> data;
    private Application application;

    public PictureGalleryViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void executeGetImages(int pageNumber){
        data=PictureGalleryRepository.getInstance().getGallleryImages(application,pageNumber);
    }
    public LiveData<Response<GalleryResponse>> getData() {
        return data;
    }
}
