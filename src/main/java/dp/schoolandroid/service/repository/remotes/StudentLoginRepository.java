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
import dp.schoolandroid.service.model.request.StudentRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StudentLoginRepository {
    private String bearerToken;
    private static StudentLoginRepository instance;

    private StudentLoginRepository() {
    }

    public static StudentLoginRepository getInstance() {
        if (instance == null) {
            instance = new StudentLoginRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<StudentResponse>> loginAsStudent(final Application application, String ssn, String password) {
        final MutableLiveData<Response<StudentResponse>> data = new MutableLiveData<>();
        StudentRequest studentLoginRequest = getStudentLoginRequest(ssn, password);
        GetApiInterfaces.getInstance().getApiInterfaces(application).loginAsStudent(ConfigurationFile.Constants.API_KEY, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, studentLoginRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<StudentResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<StudentResponse> studentResponseResponse) {
                        data.setValue(studentResponseResponse);
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
    public LiveData<Response<ForgetPasswordResponse>> forgetPasswordStudent(final Application application, final String phoneNumber) {

        ForgetPasswordRequest forgetPasswordRequest = getStudentPasswordRequest(phoneNumber);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).forgetPasswordStudent(ConfigurationFile.Constants.API_KEY, ConfigurationFile.Constants.CONTENT_TYPE,
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
    public LiveData<Response<ForgetPasswordResponse>> changePasswordStudent(final Application application
            , final String oldPassword, final String newPassword, final String newPasswordConfirmation) {
        setBearerToken(application);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        ChangePasswordRequest changePasswordStudentRequest = getStudentChangePasswordRequest(oldPassword, newPassword, newPasswordConfirmation);
        GetApiInterfaces.getInstance().getApiInterfaces(application).changePasswordStudent(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, changePasswordStudentRequest).subscribeOn(Schedulers.io())
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

    private ChangePasswordRequest getStudentChangePasswordRequest(String oldPassword, String newPassword, String newPasswordConfirmation) {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setOldPassword(oldPassword);
        changePasswordRequest.setNewPassword(newPassword);
        changePasswordRequest.setNewPasswordConfirmation(newPasswordConfirmation);
        return changePasswordRequest;
    }

    private ForgetPasswordRequest getStudentPasswordRequest(String phoneNumber) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setSsn(phoneNumber);
        return teacherLoginRequest;
    }

    private StudentRequest getStudentLoginRequest(String ssn, String password) {
        StudentRequest studentLoginRequest = new StudentRequest();
        studentLoginRequest.setStudentSSN(ssn);
        studentLoginRequest.setStudentPassword(password);
        return studentLoginRequest;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
