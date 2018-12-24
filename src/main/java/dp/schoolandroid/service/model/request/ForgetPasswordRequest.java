package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordRequest {

    @SerializedName("phone")
    private String phone;

    @SerializedName("code")
    private String code;

    @SerializedName("ssn")
    private String ssn;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
