package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.net.URL;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.request.QuizRequest;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AnnoncementsRepository {
    private static AnnoncementsRepository instance;
    private String bearerToken;

    private AnnoncementsRepository() {
    }

    public static AnnoncementsRepository getInstance() {
        if (instance == null) {
            instance = new AnnoncementsRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<QuizRequest>> createNewAnnoncement(final Application application, int sessionId, String title, String content, URL uploadedFilePath) {
        setBearerToken(application);
        QuizRequest quizRequest = getQuizRequest(title, content, uploadedFilePath);
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

    private QuizRequest getQuizRequest(String title, String content, URL uploadedFilePath) {
        QuizRequest quizRequest = new QuizRequest();
        quizRequest.setQuizTitle(title);
        quizRequest.setQuizContent(content);
        quizRequest.setUploadedFilePath(uploadedFilePath);
        return quizRequest;
    }
}
