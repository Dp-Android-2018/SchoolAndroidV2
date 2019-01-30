package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.io.File;
import java.net.URL;

import dp.schoolandroid.service.model.request.QuizRequest;
import dp.schoolandroid.service.model.response.SessionResponse;
import dp.schoolandroid.service.repository.remotes.ClassRepository;
import dp.schoolandroid.service.repository.remotes.StudentQuizRepository;
import retrofit2.Response;
import retrofit2.http.Url;

public class StudentQuizViewModel extends AndroidViewModel {
    private LiveData<Response<QuizRequest>> data;
    private Application application;

    public StudentQuizViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void executEcreateNewQuiz(int sessionId, String title, String content, int grade, URL uploadedFilePath) {
        data = StudentQuizRepository.getInstance().createNewQuiz(application, sessionId,title,content,grade, uploadedFilePath);
    }

    public LiveData<Response<QuizRequest>> getData() {
        return data;
    }
}
