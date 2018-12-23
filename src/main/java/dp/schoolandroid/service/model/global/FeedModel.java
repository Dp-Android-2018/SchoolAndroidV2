package dp.schoolandroid.service.model.global;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FeedModel implements Parcelable{

    public FeedModel(String title, String subTitle, String image, String details) {
        this.title = title;
        this.subTitle = subTitle;
        this.image = image;
        this.details = details;
    }

    @SerializedName("title")
    private String title;

    @SerializedName("sub_title")
    private String subTitle;

    @SerializedName("image")
    private String image;

    @SerializedName("details")
    private String details;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.subTitle);
        dest.writeString(this.image);
        dest.writeString(this.details);
    }
    public FeedModel(Parcel in) {
        title = in.readString();
        subTitle = in.readString();
        image = in.readString();
        details = in.readString();
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
