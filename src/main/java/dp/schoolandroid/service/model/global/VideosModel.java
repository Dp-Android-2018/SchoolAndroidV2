package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideosModel {

    @SerializedName("caption")
    private String videoCaption;

    @SerializedName("url")
    private String videoUrl;

    public String getVideoCaption() {
        return videoCaption;
    }

    public void setVideoCaption(String videoCaption) {
        this.videoCaption = videoCaption;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
