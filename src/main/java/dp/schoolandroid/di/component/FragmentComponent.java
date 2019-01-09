package dp.schoolandroid.di.component;

import dagger.Component;
import dp.schoolandroid.view.ui.activity.StudentHomeActivity;
import dp.schoolandroid.view.ui.activity.TeacherHomeActivity;

@Component
public interface FragmentComponent {

    void inject(TeacherHomeActivity teacherHomeActivity);
    void inject(StudentHomeActivity studentHomeActivity);

}
