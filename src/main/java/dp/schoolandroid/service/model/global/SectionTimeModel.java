package dp.schoolandroid.service.model.global;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SectionTimeModel implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.arrayId);
        dest.writeString(this.className);
        dest.writeString(this.teacherName);
        dest.writeString(this.grade);
        dest.writeString(this.studentsCount);
        dest.writeString(this.from);
        dest.writeString(this.to);
    }
    public SectionTimeModel(Parcel in) {
        arrayId = in.readString();
        className = in.readString();
        teacherName = in.readString();
        grade = in.readString();
        studentsCount = in.readString();
        from = in.readString();
        to = in.readString();
    }
    public static final Creator<SectionTimeModel> CREATOR = new Creator<SectionTimeModel>() {
        @Override
        public SectionTimeModel createFromParcel(Parcel in) {
            return new SectionTimeModel(in);
        }
        @Override
        public SectionTimeModel[] newArray(int size) {
            return new SectionTimeModel[size];
        }
    };
}
