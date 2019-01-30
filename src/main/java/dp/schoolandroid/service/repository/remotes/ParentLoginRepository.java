package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.ParentRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.parentresponse.ParentResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ParentLoginRepository {
    private static ParentLoginRepository instance;

    private ParentLoginRepository() {
    }

    public static ParentLoginRepository getInstance() {
        if (instance == null) {
            instance = new ParentLoginRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ParentResponse>> loginAsParent(final Application application, String phone, String password) {
        final MutableLiveData<Response<ParentResponse>> data = new MutableLiveData<>();
        ParentRequest parentLoginRequest = getParenttLoginRequest(phone, password);
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsParent(ConfigurationFile.Constants.API_KEY, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, parentLoginRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ParentResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ParentResponse> parentResponseResponse) {
                        data.setValue(parentResponseResponse);
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

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordParent(final Application application, final String phoneNumber) {
        ForgetPasswordRequest forgetPasswordRequest = getParentPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordParent(ConfigurationFile.Constants.API_KEY, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, forgetPasswordRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ForgetPasswordResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ForgetPasswordResponse> forgetPasswordResponseResponse) {
                        data.setValue(forgetPasswordResponseResponse);
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

    private ForgetPasswordRequest getParentPasswordRequest(String phoneNumber) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        return teacherLoginRequest;
    }

    private ParentRequest getParenttLoginRequest(String phone, String password) {
        ParentRequest parenttLoginRequest = new ParentRequest();
        parenttLoginRequest.setParentPhone(phone);
        parenttLoginRequest.setParentPassword(password);
        return parenttLoginRequest;
    }
}
