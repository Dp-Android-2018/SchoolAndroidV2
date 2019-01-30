package dp.schoolandroid.service.model.response.teacherresponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.SessionHistoryResponseModel;
import dp.schoolandroid.service.model.response.ResponseData;

public class SessionHistoryResponse {
    @SerializedName("data")
    private ArrayList<SessionHistoryResponseModel> sessionHistoryResponseModel;

    public ArrayList<SessionHistoryResponseModel> getSessionHistoryResponseModel() {
        return sessionHistoryResponseModel;
    }

    public void setSessionHistoryResponseModel(ArrayList<SessionHistoryResponseModel> sessionHistoryResponseModel) {
        this.sessionHistoryResponseModel = sessionHistoryResponseModel;
    }
}
