package dp.schoolandroid.service.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("image")
    private String image;

    @SerializedName("address")
    private String address;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthday")
    private String teacherBirthDate;

    @SerializedName("birth_date")
    private String studentBirthDate;

    @SerializedName("api_token")
    private String apiToken;

    @SerializedName("description")
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("nationality")
    private String nationality;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("branch_id")
    private int branchId;

    @SerializedName("phone")
    private String phone;

    @SerializedName("device_token")
    private String deviceToken;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    public void setImage(String image){
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

    public String getTeacherBirthDate() {
        return teacherBirthDate;
    }

    public void setTeacherBirthDate(String teacherBirthDate) {
        this.teacherBirthDate = teacherBirthDate;
    }

    public String getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(String studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public void setApiToken(String apiToken){
        this.apiToken = apiToken;
    }

    public String getApiToken(){
        return apiToken;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    public String getNationality(){
        return nationality;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setBranchId(int branchId){
        this.branchId = branchId;
    }

    public int getBranchId(){
        return branchId;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setDeviceToken(String deviceToken){
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken(){
        return deviceToken;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}
