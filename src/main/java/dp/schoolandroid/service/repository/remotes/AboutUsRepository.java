package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.service.model.response.ContactUsResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AboutUsRepository {
    private String bearerToken;

    public static AboutUsRepository getInstance() {
        return new AboutUsRepository();
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<AboutUsResponse>> getAboutUsInfo(final Application application, String memberType) {
        setBearerToken(application,memberType);
        final MutableLiveData<Response<AboutUsResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getAboutUsData(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<AboutUsResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<AboutUsResponse> aboutUsResponseResponse) {
                        data.setValue(aboutUsResponseResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return data;
    }

    private void setBearerToken(Application application, String memberType) {
        if (memberType.equals(ConfigurationFile.Constants.TEACHER_Key_VALUE)){
            CustomUtils customUtils = new CustomUtils(application);
            this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
        }else if (memberType.equals(ConfigurationFile.Constants.STUDENT_Key_VALUE)){
            CustomUtils customUtils = new CustomUtils(application);
            this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedStudentData().getStudentResponseData().getApiToken();
        }
    }
}
