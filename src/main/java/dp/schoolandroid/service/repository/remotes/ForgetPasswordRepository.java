package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.request.ForgetPasswordRequest;
import dp.schoolandroid.service.model.response.ForgetPasswordResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/*
* this class is responsible for initialize Forget Password Repository
* generating Password Reset Token for Teacher
* generating Password Reset Token for parent
* generating Password Reset Token for student
* */

public class ForgetPasswordRepository {

    /*private static ForgetPasswordRepository instance;

    public static ForgetPasswordRepository getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordRepository();
        }
        return instance;
    }*/

    @Inject
    public ForgetPasswordRepository() {
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> generatePasswordResetTokenTeacher(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber, code);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenTeacher(ConfigurationFile.Constants.API_KEY,ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, forgetPasswordRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data::setValue);
        return data;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> generatePasswordResetTokenParent(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getTeacherPasswordRequest(phoneNumber, code);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenParent(ConfigurationFile.Constants.API_KEY,ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, forgetPasswordRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data::setValue);
        return data;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<ForgetPasswordResponse>> generatePasswordResetTokenStudent(final Application application, final String phoneNumber, final String code) {
        ForgetPasswordRequest forgetPasswordRequest = getStudentPasswordRequest(phoneNumber, code);
        final MutableLiveData<Response<ForgetPasswordResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).generatePasswordResetTokenStudent(ConfigurationFile.Constants.API_KEY,ConfigurationFile.Constants.CONTENT_TYPE,
                ConfigurationFile.Constants.ACCEPT, forgetPasswordRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data::setValue);
        return data;
    }

    private ForgetPasswordRequest getTeacherPasswordRequest(String phoneNumber, String code) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setPhone(phoneNumber);
        teacherLoginRequest.setCode(code);
        return teacherLoginRequest;
    }

    private ForgetPasswordRequest getStudentPasswordRequest(String phoneNumber, String code) {
        ForgetPasswordRequest teacherLoginRequest = new ForgetPasswordRequest();
        teacherLoginRequest.setSsn(phoneNumber);
        teacherLoginRequest.setCode(code);
        return teacherLoginRequest;
    }
}

