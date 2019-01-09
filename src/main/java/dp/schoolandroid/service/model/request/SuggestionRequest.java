package dp.schoolandroid.service.model.request;

import com.google.gson.annotations.SerializedName;

public class SuggestionRequest {
    @SerializedName("title")
    private String suggestionTitle;

    @SerializedName("description")
    private String suggestionDescription;

    public String getSuggestionTitle() {
        return suggestionTitle;
    }

    public void setSuggestionTitle(String suggestionTitle) {
        this.suggestionTitle = suggestionTitle;
    }

    public String getSuggestionDescription() {
        return suggestionDescription;
    }

    public void setSuggestionDescription(String suggestionDescription) {
        this.suggestionDescription = suggestionDescription;
    }
}
