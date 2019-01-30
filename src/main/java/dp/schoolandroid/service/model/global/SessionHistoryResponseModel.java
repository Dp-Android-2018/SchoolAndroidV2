package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SessionHistoryResponseModel {

    @SerializedName("id")
    private int sessionId;

    @SerializedName("teacher")
    private String sessionTeacherName;

    @SerializedName("date")
    private String sessionDate;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionTeacherName() {
        return sessionTeacherName;
    }

    public void setSessionTeacherName(String sessionTeacherName) {
        this.sessionTeacherName = sessionTeacherName;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }
}
