package dp.schoolandroid.service.repository.remotes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.service.model.response.teacherresponse.AssignmentHistoryResponse;
import dp.schoolandroid.service.model.response.teacherresponse.SessionHistoryResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TeacherAssignmentRepository {
    private static TeacherAssignmentRepository instance;
    private String bearerToken;

    private TeacherAssignmentRepository() {
    }

    public static TeacherAssignmentRepository getInstance() {
        if (instance == null) {
            instance = new TeacherAssignmentRepository();
        }
        return instance;
    }

    public LiveData<Response<AssignmentHistoryResponse>> listQuizzesHistoryForSession(final Application application, int sessionId) {
        setBearerToken(application);
        final MutableLiveData<Response<AssignmentHistoryResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).listQuizzesHistory(ConfigurationFile.Constants.API_KEY, bearerToken, ConfigurationFile.Constants.CONTENT_TYPE
                , ConfigurationFile.Constants.ACCEPT, sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<AssignmentHistoryResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<AssignmentHistoryResponse> assignmentHistoryResponseResponse) {
                        data.setValue(assignmentHistoryResponseResponse);
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
