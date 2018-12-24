package dp.schoolandroid.service.model.global;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeacherSchedule {

    @SerializedName("saturday")
    private ArrayList<SectionTimeModel> sat;

    @SerializedName("sunday")
    private ArrayList<SectionTimeModel> sun;

    @SerializedName("monday")
    private ArrayList<SectionTimeModel> mon;

    @SerializedName("tuesday")
    private ArrayList<SectionTimeModel> tue;

    @SerializedName("wednesday")
    private ArrayList<SectionTimeModel> wed;

    @SerializedName("thursday")
    private ArrayList<SectionTimeModel> thu;

    @SerializedName("friday")
    private ArrayList<SectionTimeModel> fri;

    public ArrayList<SectionTimeModel> getSat() {
        return sat;
    }

    public ArrayList<SectionTimeModel> getSun() {
        return sun;
    }

    public ArrayList<SectionTimeModel> getMon() {
        return mon;
    }

    public ArrayList<SectionTimeModel> getTue() {
        return tue;
    }

    public ArrayList<SectionTimeModel> getWed() {
        return wed;
    }

    public ArrayList<SectionTimeModel> getThu() {
        return thu;
    }

    public ArrayList<SectionTimeModel> getFri() {
        return fri;
    }

}
