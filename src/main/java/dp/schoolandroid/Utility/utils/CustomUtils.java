package dp.schoolandroid.Utility.utils;

import android.app.Application;
import android.content.Context;

import dp.schoolandroid.Utility.SharedPreferenceHandler;
import dp.schoolandroid.service.model.response.studentresponse.StudentResponse;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherResponse;
import dp.schoolandroid.service.repository.remotes.GetApiInterfaces;
import dp.schoolandroid.service.repository.remotes.GetSharedPreference;

public class CustomUtils {
    private SharedPreferenceHandler prefrenceUtils;

    public CustomUtils(Application application) {
        this.prefrenceUtils = GetSharedPreference.getInstance().getSharedPreference(application);
    }

    public TeacherResponse getSavedTeacherData() {
        return (TeacherResponse) prefrenceUtils.getSavedObject(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME, TeacherResponse.class);
    }

    public void saveTeacherDataToPrefs(TeacherResponse data) {
        prefrenceUtils.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME, data);
    }

    public void saveStudentDataToPrefs(StudentResponse data) {
        prefrenceUtils.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME, data);
    }

    public void saveMemberTypeToPrefs(String objName,String objValue){
        prefrenceUtils.saveMemberTypeSharedPreferences(objName,objValue);
    }

    public String getSavedMemberTypeObject(String objName){
        return prefrenceUtils.getSavedMemberTypeObject(objName);
    }

    public StudentResponse getSavedStudentData() {
        return (StudentResponse) prefrenceUtils.getSavedObject(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME, StudentResponse.class);
    }

    public void clearSharedPref() {
        prefrenceUtils.clearToken();
    }
}
