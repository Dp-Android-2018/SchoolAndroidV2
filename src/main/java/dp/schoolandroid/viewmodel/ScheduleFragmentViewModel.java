package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import dp.schoolandroid.service.repository.remotes.TeacherGetScheduleRepository;

public class ScheduleFragmentViewModel extends AndroidViewModel {
    private final LiveData<TeacherScheduleResponse> data;

    public ScheduleFragmentViewModel(@NonNull Application application) {
        super(application);
        data=TeacherGetScheduleRepository.getInstance().getTeacherSchedule(application);
    }

    public LiveData<TeacherScheduleResponse> getData() {
        return data;
    }

}
