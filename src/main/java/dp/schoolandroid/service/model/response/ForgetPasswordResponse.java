package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    @SerializedName("error")
    private String error;

    @SerializedName("seconds_left")
    private String secondsLeft;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(String secondsLeft) {
        this.secondsLeft = secondsLeft;
    }
}
