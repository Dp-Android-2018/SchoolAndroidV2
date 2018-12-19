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

    public void setSat(ArrayList<SectionTimeModel> sat) {
        this.sat = sat;
    }

    public ArrayList<SectionTimeModel> getSun() {
        return sun;
    }

    public void setSun(ArrayList<SectionTimeModel> sun) {
        this.sun = sun;
    }

    public ArrayList<SectionTimeModel> getMon() {
        return mon;
    }

    public void setMon(ArrayList<SectionTimeModel> mon) {
        this.mon = mon;
    }

    public ArrayList<SectionTimeModel> getTue() {
        return tue;
    }

    public void setTue(ArrayList<SectionTimeModel> tue) {
        this.tue = tue;
    }

    public ArrayList<SectionTimeModel> getWed() {
        return wed;
    }

    public void setWed(ArrayList<SectionTimeModel> wed) {
        this.wed = wed;
    }

    public ArrayList<SectionTimeModel> getThu() {
        return thu;
    }

    public void setThu(ArrayList<SectionTimeModel> thu) {
        this.thu = thu;
    }

    public ArrayList<SectionTimeModel> getFri() {
        return fri;
    }

    public void setFri(ArrayList<SectionTimeModel> fri) {
        this.fri = fri;
    }
}
