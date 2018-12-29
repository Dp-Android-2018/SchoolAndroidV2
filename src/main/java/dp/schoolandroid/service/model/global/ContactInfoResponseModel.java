package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactInfoResponseModel {

    @SerializedName("lat")
    private String locationLatitude;

    @SerializedName("lng")
    private String locationLongitude;

    @SerializedName("social_networks")
    private SocialNetworksModel companySocialNetworks;

    @SerializedName("phone_numbers")
    private ArrayList<String> companyPhoneNumbers;

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public SocialNetworksModel getCompanySocialNetworks() {
        return companySocialNetworks;
    }

    public void setCompanySocialNetworks(SocialNetworksModel companySocialNetworks) {
        this.companySocialNetworks = companySocialNetworks;
    }

    public ArrayList<String> getCompanyPhoneNumbers() {
        return companyPhoneNumbers;
    }

    public void setCompanyPhoneNumbers(ArrayList<String> companyPhoneNumbers) {
        this.companyPhoneNumbers = companyPhoneNumbers;
    }
}
