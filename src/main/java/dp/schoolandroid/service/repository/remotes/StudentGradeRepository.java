package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.request.GradeRequest;
import dp.schoolandroid.service.model.response.SessionResponseData;
import dp.schoolandroid.service.model.response.teacherresponse.QuizHistoryResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StudentGradeRepository {
    private static StudentGradeRepository instance;
    private String bearerToken;

    private StudentGradeRepository() {
    }

    public static StudentGradeRepository getInstance() {
        if (instance == null) {
            instance = new StudentGradeRepository();
        }
        return instance;
    }

    public LiveData<Response<SessionResponseData>> givePointsToQuizResponse(final Application application, int quizId, int quizResponseId, int points) {
        setBearerToken(application);
        final MutableLiveData<Response<SessionResponseData>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).givePointsToQuizResponse(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
                , ConfigurationFile.Constants.ACCEPT, quizId, quizResponseId,getGradeQequest(points))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<SessionResponseData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<SessionResponseData> sessionResponseDataResponse) {
                        data.setValue(sessionResponseDataResponse);
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

    private GradeRequest getGradeQequest(int points) {
        GradeRequest gradeRequest=new GradeRequest();
        gradeRequest.setQuizPoints(points);
        return gradeRequest;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils = new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER + customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
