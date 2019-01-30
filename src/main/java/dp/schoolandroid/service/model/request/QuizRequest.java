package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.net.URL;

import retrofit2.http.Url;

public class QuizRequest {
    @SerializedName("title")
    private String quizTitle;

    @SerializedName("content")
    private String quizContent;

    @SerializedName("grade")
    private int quizGrade;

    @SerializedName("attachment")
    private URL uploadedFilePath;

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizContent() {
        return quizContent;
    }

    public void setQuizContent(String quizContent) {
        this.quizContent = quizContent;
    }

    public int getQuizGrade() {
        return quizGrade;
    }

    public void setQuizGrade(int quizGrade) {
        this.quizGrade = quizGrade;
    }

    public URL getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(URL uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }
}
