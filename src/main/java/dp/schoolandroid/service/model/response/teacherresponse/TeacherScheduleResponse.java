package dp.schoolandroid.service.model.response.teacherresponse;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.global.TeacherSchedule;

public class TeacherScheduleResponse {
    @SerializedName("data")
    private TeacherSchedule data;

    public TeacherSchedule getData() {
        return data;
    }
}
