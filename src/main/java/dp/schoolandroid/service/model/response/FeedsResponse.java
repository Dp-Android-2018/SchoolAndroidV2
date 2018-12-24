package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.FeedModel;

public class FeedsResponse {

    @SerializedName("data")
    private ArrayList<FeedModel> newsFeedResponseData;

    public ArrayList<FeedModel> getNewsFeedResponseData() {
        return newsFeedResponseData;
    }
}
