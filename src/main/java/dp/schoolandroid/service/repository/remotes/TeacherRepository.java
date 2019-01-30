package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.request.ChangePasswordRequest;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.TeacherRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TeacherRepository {
    private String bearerToken;

    private static TeacherRepository instance;

    private TeacherRepository() {
    }

    public static TeacherRepository getInstance() {
        if (instance == null) {
            instance = new TeacherRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<TeacherResponse>> loginAsTeacher(final Application application, String phoneNumber, String password) {
        TeacherRequest teacherLoginRequest = getTeacherLoginRequest(phoneNumber, password);
        final MutableLiveData<Response<TeacherResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsTeacher(ConfigurationFile.Constants.API_KEY, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, teacherLoginRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<TeacherResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<TeacherResponse> teacherResponseResponse) {
                        data.setValue(teacherResponseResponse);
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
    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordTeacher(final Application application, final String phoneNumber) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordTeacher(ConfigurationFile.Constants.API_KEY, ConfigurationFile.Constants.CONTENT_TYPE,
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

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> changePasswordTeacher(final Application application
            , final String oldPassword, final String newPassword, final String newPasswordConfirmation) {
        setBearerToken(application);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        ChangePasswordRequest changePasswordTeacherRequest = getTeacherChangePasswordRequest(oldPassword, newPassword, newPasswordConfirmation);
        GetApiInterfaces.getInstance().getApiInterfaces(application).changePasswordTeacher(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, changePasswordTeacherRequest).subscribeOn(Schedulers.io())
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

    private ChangePasswordRequest getTeacherChangePasswordRequest(String oldPassword, String newPassword, String newPasswordConfirmation) {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setOldPassword(oldPassword);
        changePasswordRequest.setNewPassword(newPassword);
        changePasswordRequest.setNewPasswordConfirmation(newPasswordConfirmation);
        return changePasswordRequest;
    }

    private ForgetPasswordRequest getTeacherPasswordRequest(String phoneNumber) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        return teacherLoginRequest;
    }


    private TeacherRequest getTeacherLoginRequest(String phoneNumber, String password) {
        TeacherRequest teacherLoginRequest = new TeacherRequest();
        teacherLoginRequest.setTeacherPhone(phoneNumber);
        teacherLoginRequest.setTeacherPassword(password);
        return teacherLoginRequest;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }

}
