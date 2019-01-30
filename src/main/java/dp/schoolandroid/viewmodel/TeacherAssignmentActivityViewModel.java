package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.teacherresponse.AssignmentHistoryResponse;
import dp.schoolandroid.service.repository.remotes.TeacherAssignmentRepository;
import retrofit2.Response;

public class TeacherAssignmentActivityViewModel extends AndroidViewModel {
    private Application application;
    private LiveData<Response<AssignmentHistoryResponse>> data;

    public TeacherAssignmentActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void executeListQuizzesHistory(int sessionId) {
      data=  TeacherAssignmentRepository.getInstance().listQuizzesHistoryForSession(application, sessionId);
    }

    public LiveData<Response<AssignmentHistoryResponse>> getData() {
        return data;
    }
}
