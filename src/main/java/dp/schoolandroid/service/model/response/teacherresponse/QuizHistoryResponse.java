package dp.schoolandroid.service.model.response.teacherresponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.QuizHistoryResponseModel;
import dp.schoolandroid.service.model.global.SessionHistoryResponseModel;

public class QuizHistoryResponse {
    @SerializedName("data")
    private ArrayList<QuizHistoryResponseModel> quizHistoryResponseModels;

    public ArrayList<QuizHistoryResponseModel> getQuizHistoryResponseModels() {
        return quizHistoryResponseModels;
    }

    public void setQuizHistoryResponseModels(ArrayList<QuizHistoryResponseModel> quizHistoryResponseModels) {
        this.quizHistoryResponseModels = quizHistoryResponseModels;
    }
}
