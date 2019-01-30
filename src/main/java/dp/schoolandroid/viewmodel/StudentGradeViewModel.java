package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.SessionResponseData;
import dp.schoolandroid.service.repository.remotes.StudentGradeRepository;
import retrofit2.Response;

public class StudentGradeViewModel extends AndroidViewModel {
    private Application application;
    private LiveData<Response<SessionResponseData>> data;

    public StudentGradeViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void executeGivePointsToQuizResponse(int quizId, int quizResponseId, int points){
        data = StudentGradeRepository.getInstance().givePointsToQuizResponse(application, quizId, quizResponseId,points);
    }

    public LiveData<Response<SessionResponseData>> getData() {
        return data;
    }
}
