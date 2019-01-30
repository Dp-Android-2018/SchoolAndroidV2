package dp.schoolandroid.service.model.response.teacherresponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.AssignmentHistoryResponseModel;

public class AssignmentHistoryResponse {
    @SerializedName("data")
    private ArrayList<AssignmentHistoryResponseModel> assignmentHistoryResponseModel;

    public ArrayList<AssignmentHistoryResponseModel> getAssignmentHistoryResponseModel() {
        return assignmentHistoryResponseModel;
    }

    public void setAssignmentHistoryResponseModel(ArrayList<AssignmentHistoryResponseModel> assignmentHistoryResponseModel) {
        this.assignmentHistoryResponseModel = assignmentHistoryResponseModel;
    }
}
