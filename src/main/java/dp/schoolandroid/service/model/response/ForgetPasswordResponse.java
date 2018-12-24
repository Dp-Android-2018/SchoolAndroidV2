package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponse {

    @SerializedName("message")
    private String forgetPasswordResponseMessage;

    @SerializedName("token")
    private String forgetPasswordResponseToken;

    @SerializedName("error")
    private String forgetPasswordResponseError;

    @SerializedName("seconds_left")
    private String forgetPasswordResponseSecondsLeft;

    public String getForgetPasswordResponseMessage() {
        return forgetPasswordResponseMessage;
    }

    public void setForgetPasswordResponseMessage(String message) {
        this.forgetPasswordResponseMessage = message;
    }

    public String getForgetPasswordResponseToken() {
        return forgetPasswordResponseToken;
    }

    public void setForgetPasswordResponseToken(String forgetPasswordResponseToken) {
        this.forgetPasswordResponseToken = forgetPasswordResponseToken;
    }

    public String getForgetPasswordResponseError() {
        return forgetPasswordResponseError;
    }

    public void setForgetPasswordResponseError(String forgetPasswordResponseError) {
        this.forgetPasswordResponseError = forgetPasswordResponseError;
    }

    public String getForgetPasswordResponseSecondsLeft() {
        return forgetPasswordResponseSecondsLeft;
    }

    public void setForgetPasswordResponseSecondsLeft(String forgetPasswordResponseSecondsLeft) {
        this.forgetPasswordResponseSecondsLeft = forgetPasswordResponseSecondsLeft;
    }
}
