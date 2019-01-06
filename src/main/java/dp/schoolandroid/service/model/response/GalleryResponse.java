package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import dp.schoolandroid.service.model.global.LinksModel;
import dp.schoolandroid.service.model.global.MetaDataModel;

public class GalleryResponse {

    @SerializedName("data")
    private ArrayList<String> pageImages;

    @SerializedName("links")
    private LinksModel pageLinks;

    @SerializedName("meta")
    private MetaDataModel metaData;

    public ArrayList<String> getPageImages() {
        return pageImages;
    }

    public void setPageImages(ArrayList<String> pageImages) {
        this.pageImages = pageImages;
    }

    public LinksModel getPageLinks() {
        return pageLinks;
    }

    public void setPageLinks(LinksModel pageLinks) {
        this.pageLinks = pageLinks;
    }

    public MetaDataModel getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaDataModel metaData) {
        this.metaData = metaData;
    }
}
