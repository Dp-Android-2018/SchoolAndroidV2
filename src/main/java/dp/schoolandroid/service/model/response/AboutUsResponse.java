package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.global.ContactInfoResponseModel;

public class AboutUsResponse {
    @SerializedName("data")
    private String aboutUsInfoResponseModel;

    @SerializedName("message")
    private String suggestionResponseModel;

    public String getAboutUsInfoResponseModel() {
        return aboutUsInfoResponseModel;
    }

    public void setAboutUsInfoResponseModel(String aboutUsInfoResponseModel) {
        this.aboutUsInfoResponseModel = aboutUsInfoResponseModel;
    }

    public String getSuggestionResponseModel() {
        return suggestionResponseModel;
    }

    public void setSuggestionResponseModel(String suggestionResponseModel) {
        this.suggestionResponseModel = suggestionResponseModel;
    }
}
