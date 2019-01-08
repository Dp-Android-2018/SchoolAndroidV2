package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.global.MetaDataModel;
import dp.schoolandroid.service.model.response.GalleryResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PictureGalleryRepository {
    private static PictureGalleryRepository instance;
    private String bearerToken;

    private PictureGalleryRepository() {
    }

    public static PictureGalleryRepository getInstance() {
        if (instance == null) {
            instance = new PictureGalleryRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<GalleryResponse>> getGallleryImages(final Application application, int pageNumber) {
        setBearerToken(application);
        final MutableLiveData<Response<GalleryResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getGallleryImages(ConfigurationFile.Constants.API_KEY,bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
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
