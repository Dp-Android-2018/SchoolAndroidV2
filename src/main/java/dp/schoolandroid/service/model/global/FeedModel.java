package dp.schoolandroid.service.model.global;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FeedModel implements Parcelable{

    @SerializedName("title")
    private String newsFeedTitle;

    @SerializedName("sub_title")
    private String newsFeedSubTitle;

    @SerializedName("image")
    private String newsFeedImage;

    @SerializedName("details")
    private String newsFeedDetails;

    public String getNewsFeedTitle() {
        return newsFeedTitle;
    }

    public void setNewsFeedTitle(String newsFeedTitle) {
        this.newsFeedTitle = newsFeedTitle;
    }

    public String getNewsFeedSubTitle() {
        return newsFeedSubTitle;
    }

    public void setNewsFeedSubTitle(String newsFeedSubTitle) {
        this.newsFeedSubTitle = newsFeedSubTitle;
    }

    public String getNewsFeedImage() {
        return newsFeedImage;
    }

    public void setNewsFeedImage(String newsFeedImage) {
        this.newsFeedImage = newsFeedImage;
    }

    public String getNewsFeedDetails() {
        return newsFeedDetails;
    }

    public void setNewsFeedDetails(String newsFeedDetails) {
        this.newsFeedDetails = newsFeedDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.newsFeedTitle);
        dest.writeString(this.newsFeedSubTitle);
        dest.writeString(this.newsFeedImage);
        dest.writeString(this.newsFeedDetails);
    }
    public FeedModel(Parcel in) {
        newsFeedTitle = in.readString();
        newsFeedSubTitle = in.readString();
        newsFeedImage = in.readString();
        newsFeedDetails = in.readString();
    }
    public static final Creator<FeedModel> CREATOR = new Creator<FeedModel>() {
        @Override
        public FeedModel createFromParcel(Parcel in) {
            return new FeedModel(in);
        }
        @Override
        public FeedModel[] newArray(int size) {
            return new FeedModel[size];
        }
    };

}
