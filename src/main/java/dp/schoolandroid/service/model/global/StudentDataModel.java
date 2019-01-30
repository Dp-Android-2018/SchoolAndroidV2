package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

public class StudentDataModel {
    @SerializedName("name")
    private String studentName;

    @SerializedName("image")
    private String studentImage;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }
}
