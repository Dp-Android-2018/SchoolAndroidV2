package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.request.ResetPasswordRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/*
 * this class is responsible for initialize Reset Password Repository
 * resetting Password for Teacher
 * resetting Password for parent
 * resetting Password for student
 * */

public class ResetPasswordRepository {
    private static ResetPasswordRepository instance;

    public static ResetPasswordRepository getInstance() {
        if (instance == null) {
            instance = new ResetPasswordRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> resetPasswordTeacher(final Application application, final String apiToken
            , final String phoneNumber, final String password, final String passwordConfirmation) {
        ResetPasswordRequest resetPasswordTeacherRequest = getResetPasswordRequest(phoneNumber, password, passwordConfirmation);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).resetPasswordTeacher(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, apiToken, resetPasswordTeacherRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forgetPasswordResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(forgetPasswordResponseResponse);
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> resetPasswordParent(final Application application, final String apiToken
            , final String phoneNumber, final String password, final String passwordConfirmation) {
        ResetPasswordRequest resetPasswordParentRequest = getResetPasswordRequest(phoneNumber, password, passwordConfirmation);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).resetPasswordParent(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, apiToken, resetPasswordParentRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forgetPasswordResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(forgetPasswordResponseResponse);
                });
        return data;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> resetPasswordStudent(final Application application, final String apiToken
            , final String ssn, final String password, final String passwordConfirmation) {
        ResetPasswordRequest resetPasswordStudentRequest = getResetPasswordStudentRequest(ssn, password, passwordConfirmation);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).resetPasswordStudent(ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, apiToken, resetPasswordStudentRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forgetPasswordResponseResponse -> {
                    SharedUtils.getInstance().cancelDialog();
                    data.setValue(forgetPasswordResponseResponse);
                });
        return data;
    }

    private ResetPasswordRequest getResetPasswordStudentRequest(String ssn, String password, String passwordConfirmation) {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setSsn(ssn);
        resetPasswordRequest.setPassword(password);
        resetPasswordRequest.setPasswordConfirmation(passwordConfirmation);
        return resetPasswordRequest;
    }

    private ResetPasswordRequest getResetPasswordRequest(String phoneNumber, String password, String passwordConfirmation) {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setPhone(phoneNumber);
        resetPasswordRequest.setPassword(password);
        resetPasswordRequest.setPasswordConfirmation(passwordConfirmation);
        return resetPasswordRequest;
    }

}
