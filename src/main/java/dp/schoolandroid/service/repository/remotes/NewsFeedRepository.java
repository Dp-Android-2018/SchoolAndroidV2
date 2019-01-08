package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.design.widget.Snackbar;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.service.model.response.FeedsResponse;
import dp.schoolandroid.view.ui.activity.MainActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class NewsFeedRepository {
    private static NewsFeedRepository instance;
    private String bearerToken;
    CustomUtils customUtils;

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
        GetApiInterfaces.getInstance().getApiInterfaces(application).getNewsFeed(ConfigurationFile.Constants.API_KEY,bearerToken,
                ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.ACCEPT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(feedsResponseResponse -> {
            SharedUtils.getInstance().cancelDialog();
            if (feedsResponseResponse != null) {
                data.setValue(feedsResponseResponse);
            }
        });
        return data;
    }

    private void setBearerToken(Application application) {
        customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
