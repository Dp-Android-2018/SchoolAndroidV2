package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.schoolandroid.service.model.global.FeedModel;

public class FeedsResponse {

    @SerializedName("data")
    private ArrayList<FeedModel> data;

    public ArrayList<FeedModel> getData() {
        return data;
    }
}
