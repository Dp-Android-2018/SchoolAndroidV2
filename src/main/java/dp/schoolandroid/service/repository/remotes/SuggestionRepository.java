package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.request.SuggestionRequest;
import dp.schoolandroid.service.model.response.AboutUsResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SuggestionRepository {

    private String bearerToken;

    private static SuggestionRepository instance;

    public static SuggestionRepository getInstance() {
        return new SuggestionRepository();
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<AboutUsResponse>> createNewSuggestion(final Application application,String title,String describtion) {
        setBearerToken(application);
        SuggestionRequest suggestionRequest=getSuggestionRequest(title,describtion);
        final MutableLiveData<Response<AboutUsResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).createNewSuggestion(ConfigurationFile.Constants.API_KEY,bearerToken, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT,suggestionRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data::setValue);
        return data;
    }

    private SuggestionRequest getSuggestionRequest(String title, String describtion) {
        SuggestionRequest suggestionRequest=new SuggestionRequest();
        suggestionRequest.setSuggestionTitle(title);
        suggestionRequest.setSuggestionDescription(describtion);
        return suggestionRequest;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
