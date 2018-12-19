package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class StudentRequest {
    @SerializedName("ssn")
    private String ssn;

    @SerializedName("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }
}
