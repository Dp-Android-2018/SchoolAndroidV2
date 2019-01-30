package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.io.File;
import java.net.URL;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.request.QuizRequest;
import dp.schoolandroid.service.model.request.SessionRequest;
import dp.schoolandroid.service.model.response.SessionResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.Url;

public class StudentQuizRepository {
    private static StudentQuizRepository instance;
    private String bearerToken;

    private StudentQuizRepository() {
    }

    public static StudentQuizRepository getInstance() {
        if (instance == null) {
            instance = new StudentQuizRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<QuizRequest>> createNewQuiz(final Application application, int sessionId, String title, String content, int grade, URL uploadedFilePath) {
        setBearerToken(application);
        QuizRequest quizRequest = getQuizRequest(title, content, grade, uploadedFilePath);
        final MutableLiveData<Response<QuizRequest>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).createNewQuiz(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
                , ConfigurationFile.Constants.ACCEPT, sessionId,quizRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<QuizRequest>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<QuizRequest> quizRequestResponse) {
                        data.setValue(quizRequestResponse);
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

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }

    private QuizRequest getQuizRequest(String title, String content, int grade, URL uploadedFilePath) {
        QuizRequest quizRequest = new QuizRequest();
        quizRequest.setQuizTitle(title);
        quizRequest.setQuizContent(content);
        quizRequest.setQuizGrade(grade);
        quizRequest.setUploadedFilePath(uploadedFilePath);
        return quizRequest;
    }
}
