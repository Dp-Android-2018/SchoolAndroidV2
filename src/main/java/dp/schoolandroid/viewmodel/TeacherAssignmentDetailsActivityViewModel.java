package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.teacherresponse.QuizHistoryResponse;
import dp.schoolandroid.service.repository.remotes.TeacherAssignmentDetailsRepository;
import retrofit2.Response;

public class TeacherAssignmentDetailsActivityViewModel extends AndroidViewModel {
    private Application application;
    private LiveData<Response<QuizHistoryResponse>> data;

    public TeacherAssignmentDetailsActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void executeGetQuizResponses(int quizId){
        data = TeacherAssignmentDetailsRepository.getInstance().getQuizResponses(application, quizId);
    }

    public LiveData<Response<QuizHistoryResponse>> getData() {
        return data;
    }
}
