package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.response.GalleryResponse;
import dp.schoolandroid.service.model.response.VideosResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class VideosRepository {
    private static VideosRepository instance;
    private String bearerToken;

    private VideosRepository() {
    }

    public static VideosRepository getInstance() {
        if (instance == null) {
            instance = new VideosRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<VideosResponse>> getAllTheVideos(final Application application, int pageNumber) {
        setBearerToken(application);
        final MutableLiveData<Response<VideosResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getAllTheVideos(ConfigurationFile.Constants.API_KEY,bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
                , ConfigurationFile.Constants.ACCEPT,pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data::setValue, throwable -> {

                });
        return data;
    }
    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
