package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class TeacherRequest {
    @SerializedName("phone")
    private String teacherPhone;

    @SerializedName("password")
    private String teacherPassword;

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String password) {
        this.teacherPassword = password;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String phone) {
        this.teacherPhone = phone;
    }
}
