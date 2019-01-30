package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class GradeRequest {
    @SerializedName("points")
    private int quizPoints;

    public int getQuizPoints() {
        return quizPoints;
    }

    public void setQuizPoints(int quizPoints) {
        this.quizPoints = quizPoints;
    }
}
