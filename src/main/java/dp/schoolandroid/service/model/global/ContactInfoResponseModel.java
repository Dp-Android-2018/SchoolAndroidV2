package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactInfoResponseModel {

    @SerializedName("lat")
    private String locationLatitude;

    @SerializedName("lng")
    private String locationLongitude;

    @SerializedName("social_networks")
    private ArrayList<SocialNetworksModel> companySocialNetworks;

    @SerializedName("phone_numbers")
    private ArrayList<Integer> companyPhoneNumbers;

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

    public ArrayList<SocialNetworksModel> getCompanySocialNetworks() {
        return companySocialNetworks;
    }

    public void setCompanySocialNetworks(ArrayList<SocialNetworksModel> companySocialNetworks) {
        this.companySocialNetworks = companySocialNetworks;
    }

    public ArrayList<Integer> getCompanyPhoneNumbers() {
        return companyPhoneNumbers;
    }

    public void setCompanyPhoneNumbers(ArrayList<Integer> companyPhoneNumbers) {
        this.companyPhoneNumbers = companyPhoneNumbers;
    }
}
