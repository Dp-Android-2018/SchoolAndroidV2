package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

import dp.schoolandroid.service.model.global.ContactInfoResponseModel;

public class ContactUsResponse {

    @SerializedName("data")
    private ContactInfoResponseModel contactInfoResponseModel;

    public ContactInfoResponseModel getContactInfo() {
        return contactInfoResponseModel;
    }

    public void setContactInfoResponseModel(ContactInfoResponseModel contactInfoResponseModel) {
        this.contactInfoResponseModel = contactInfoResponseModel;
    }
}
