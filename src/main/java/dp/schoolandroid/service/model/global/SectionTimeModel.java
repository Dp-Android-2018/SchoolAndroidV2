package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

public class SectionTimeModel {

    @SerializedName("id")
    private String arrayId;

    @SerializedName("class")
    private String className;

    @SerializedName("teacher")
    private String teacherName;

    @SerializedName("level")
    private String grade;

    @SerializedName("students_count")
    private String studentsCount;

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    public String getArrayId() {
        return arrayId;
    }

    public void setArrayId(String arrayId) {
        this.arrayId = arrayId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(String studentsCount) {
        this.studentsCount = studentsCount;
    }

    public String getFrom() {
        return from+" am";
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to+" am";
    }

    public void setTo(String to) {
        this.to = to;
    }
}
