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
    private int visibleThreshold = 5;
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
        GetApiInterfaces.getInstance().getApiInterfaces(application).getGallleryImages(bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
                , ConfigurationFile.Constants.ACCEPT,pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(galleryResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(galleryResponseResponse);
                }, throwable -> {

                });
        return data;
    }

    public RecyclerView.OnScrollListener onScrollListener(Application application, MetaDataModel metaData, ArrayList<String> pageImages){
        return new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount =gridLayoutManager.getItemCount();
                int lastVisibleItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                /*if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (metaData.getLastPage() != 1 ) {
                        // loading = true;
                        System.out.println("Load More Data Success ");
                        getGallleryImages(application,2);
                    }
                }*/
                if (((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()==pageImages.size()-1) {
                    System.out.println("Done :");
                    getGallleryImages(application,2);
                }
            }
        };
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
