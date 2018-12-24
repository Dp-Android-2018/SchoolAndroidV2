package dp.schoolandroid.service.model.response.teacherresponse;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.global.TeacherSchedule;

public class TeacherScheduleResponse {
    @SerializedName("data")
    private TeacherSchedule teacherScheduleData;

    public TeacherSchedule getTeacherScheduleData() {
        return teacherScheduleData;
    }
}
