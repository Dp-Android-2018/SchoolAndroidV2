package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.service.model.response.FeedsResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class NewsFeedRepository {
    private static NewsFeedRepository instance;
    private String bearerToken;

    private NewsFeedRepository() {
    }

    public static NewsFeedRepository getInstance() {
        if (instance == null) {
            instance = new NewsFeedRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<FeedsResponse>> getNewsFeed(final Application application) {
        setBearerToken(application);
        final MutableLiveData<Response<FeedsResponse>> data = new MutableLiveData<>();
            GetApiInterfaces.getInstance().getApiInterfaces(application).getNewsFeed(bearerToken,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.ACCEPT).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(feedsResponseResponse -> {
                SharedUtils.getInstance().cancelDialog();
                data.setValue(feedsResponseResponse);
            });
        return data;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
