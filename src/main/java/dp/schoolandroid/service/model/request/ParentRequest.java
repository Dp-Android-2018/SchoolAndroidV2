package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class ParentRequest {
    @SerializedName("phone")
    private String parentPhone;

    @SerializedName("password")
    private String parentPassword;

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String phone) {
        this.parentPhone = phone;
    }

    public String getParentPassword() {
        return parentPassword;
    }

    public void setParentPassword(String password) {
        this.parentPassword = password;
    }
}
