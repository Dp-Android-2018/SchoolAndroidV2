package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

public class FeedModel {

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
}
