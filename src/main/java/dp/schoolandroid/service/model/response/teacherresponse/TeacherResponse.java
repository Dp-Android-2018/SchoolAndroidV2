package dp.schoolandroid.service.model.response.teacherresponse;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.response.ResponseData;

public class TeacherResponse {
    @SerializedName("data")
    private ResponseData teacherResponseData;

    public ResponseData getTeacherData() {
        return teacherResponseData;
    }
}
