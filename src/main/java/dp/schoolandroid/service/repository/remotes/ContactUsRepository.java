package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.global.ContactInfoResponseModel;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.response.ContactUsResponse;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ContactUsRepository {
    private String bearerToken;

    private static ContactUsRepository instance;

    public static ContactUsRepository getInstance() {
        return new ContactUsRepository();
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ContactUsResponse>> getContactInfoForTeacher(final Application application) {
        setBearerToken(application);
        final MutableLiveData<Response<ContactUsResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getContactInfoForTeacher(bearerToken, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contactUsResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(contactUsResponseResponse);
                });
        return data;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
