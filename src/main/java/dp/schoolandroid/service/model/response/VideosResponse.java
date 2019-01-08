package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.LinksModel;
import dp.schoolandroid.service.model.global.MetaDataModel;
import dp.schoolandroid.service.model.global.VideosModel;

public class VideosResponse {
    @SerializedName("data")
    private ArrayList<VideosModel> pageVideos;

    @SerializedName("links")
    private LinksModel pageLinks;

    @SerializedName("meta")
    private MetaDataModel metaData;

    public ArrayList<VideosModel> getPageVideos() {
        return pageVideos;
    }

    public void setPageVideos(ArrayList<VideosModel> pageVideos) {
        this.pageVideos = pageVideos;
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
