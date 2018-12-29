package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

public class SocialNetworksModel {

    @SerializedName("facebook")
    private String facebookLink;

    @SerializedName("twitter")
    private String twitterLink;

    @SerializedName("instagram")
    private String instagramLink;

    @SerializedName("linkedin")
    private String linkedinLink;

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }
}
