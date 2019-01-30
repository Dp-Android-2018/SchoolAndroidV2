package dp.schoolandroid.service.model.global;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AssignmentHistoryResponseModel implements Parcelable {
    @SerializedName("id")
    private String assignmentId;

    @SerializedName("title")
    private String assignmentTitle;

    @SerializedName("grade")
    private String assignmentGrade;

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(String assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.assignmentId);
        dest.writeString(this.assignmentTitle);
        dest.writeString(this.assignmentGrade);
    }

    public AssignmentHistoryResponseModel(Parcel in) {
        assignmentId = in.readString();
        assignmentTitle = in.readString();
        assignmentGrade = in.readString();
    }

    public static final Parcelable.Creator<AssignmentHistoryResponseModel> CREATOR = new Parcelable.Creator<AssignmentHistoryResponseModel>() {
        @Override
        public AssignmentHistoryResponseModel createFromParcel(Parcel in) {
            return new AssignmentHistoryResponseModel(in);
        }

        @Override
        public AssignmentHistoryResponseModel[] newArray(int size) {
            return new AssignmentHistoryResponseModel[size];
        }
    };
}
