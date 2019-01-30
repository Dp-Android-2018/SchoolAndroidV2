package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.GalleryResponse;
import dp.schoolandroid.service.model.response.VideosResponse;
import dp.schoolandroid.service.repository.remotes.PictureGalleryRepository;
import dp.schoolandroid.service.repository.remotes.VideosRepository;
import retrofit2.Response;

public class VideosActivityViewModel extends AndroidViewModel {
    private LiveData<Response<VideosResponse>> data;
    private Application application;
    private String memberType;

    public VideosActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void executeGetVideos(int pageNumber){
        data=VideosRepository.getInstance().getAllTheVideos(application,pageNumber,memberType);
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public LiveData<Response<VideosResponse>> getData() {
        return data;
    }
}
