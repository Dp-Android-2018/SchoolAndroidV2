package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class StudentRequest {
    @SerializedName("ssn")
    private String studentSSN;

    @SerializedName("password")
    private String studentPassword;

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String password) {
        this.studentPassword = password;
    }

    public void setStudentSSN(String ssn) {
        this.studentSSN = ssn;
    }

    public String getStudentSSN() {
        return studentSSN;
    }
}
