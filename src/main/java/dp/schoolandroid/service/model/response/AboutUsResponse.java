package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.global.ContactInfoResponseModel;

public class AboutUsResponse {
    @SerializedName("data")
    private String aboutUsInfoResponseModel;

    public String getAboutUsInfoResponseModel() {
        return aboutUsInfoResponseModel;
    }

    public void setAboutUsInfoResponseModel(String aboutUsInfoResponseModel) {
        this.aboutUsInfoResponseModel = aboutUsInfoResponseModel;
    }
}
