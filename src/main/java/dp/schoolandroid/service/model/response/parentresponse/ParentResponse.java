package dp.schoolandroid.service.model.response.parentresponse;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.response.ResponseData;

public class ParentResponse {
    @SerializedName("data")
    private ResponseData parentResponseData;

    public ResponseData getParentResponseData() {
        return parentResponseData;
    }
}
