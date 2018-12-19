package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import dp.schoolandroid.service.repository.remotes.TeacherGetScheduleRepository;

public class DayFragmentViewModel extends AndroidViewModel {

    public DayFragmentViewModel(@NonNull Application application) {
        super(application);
    }

}
