package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.response.teacherresponse.AssignmentHistoryResponse;
import dp.schoolandroid.service.model.response.teacherresponse.QuizHistoryResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TeacherAssignmentDetailsRepository {
    private static TeacherAssignmentDetailsRepository instance;
    private String bearerToken;

    private TeacherAssignmentDetailsRepository() {
    }

    public static TeacherAssignmentDetailsRepository getInstance() {
        if (instance == null) {
            instance = new TeacherAssignmentDetailsRepository();
        }
        return instance;
    }

    public LiveData<Response<QuizHistoryResponse>> getQuizResponses(final Application application, int quizId) {
        setBearerToken(application);
        final MutableLiveData<Response<QuizHistoryResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getQuizResponses(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
                , ConfigurationFile.Constants.ACCEPT, quizId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<QuizHistoryResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<QuizHistoryResponse> quizHistoryResponseResponse) {
                        data.setValue(quizHistoryResponseResponse);
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
}
