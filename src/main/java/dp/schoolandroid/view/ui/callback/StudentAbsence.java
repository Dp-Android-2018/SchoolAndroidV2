package dp.schoolandroid.view.ui.callback;

import dp.schoolandroid.service.model.global.AbsentStudentModel;
import dp.schoolandroid.service.model.request.TeachingSessionRequest;

public interface StudentAbsence {

    public void updateStudentState(AbsentStudentModel student);

    public void studentOfDay(int studentId,boolean starState);
}
