package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class QuizHistoryResponseModel {
    @SerializedName("id")
    private int quizResponseId;

    @SerializedName("points")
    private String studentPoints;

    @SerializedName("is_awarded")
    private boolean isAwarded;

    @SerializedName("attachment")
    private String attachmentUrl;

    @SerializedName("student")
    private StudentDataModel studentDataModel;

    public int getQuizResponseId() {
        return quizResponseId;
    }

    public void setQuizResponseId(int quizResponseId) {
        this.quizResponseId = quizResponseId;
    }

    public String getStudentPoints() {
        return studentPoints;
    }

    public void setStudentPoints(String studentPoints) {
        this.studentPoints = studentPoints;
    }

    public boolean isAwarded() {
        return isAwarded;
    }

    public void setAwarded(boolean awarded) {
        isAwarded = awarded;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public StudentDataModel getStudentDataModel() {
        return studentDataModel;
    }

    public void setStudentDataModel(StudentDataModel studentDataModel) {
        this.studentDataModel = studentDataModel;
    }
}
