package dp.schoolandroid.service.model.response.studentresponse;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.response.ResponseData;

public class StudentResponse {
    @SerializedName("data")
    private ResponseData studentResponseData;

    public ResponseData getStudentResponseData() {
        return studentResponseData;
    }
}
