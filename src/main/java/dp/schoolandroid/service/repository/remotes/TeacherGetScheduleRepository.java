package dp.schoolandroid.service.repository.remotes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.service.model.response.teacherresponse.TeacherScheduleResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TeacherGetScheduleRepository {
    private static TeacherGetScheduleRepository instance;
    private String bearerToken;
    private TeacherGetScheduleRepository(){}

    public static TeacherGetScheduleRepository getInstance() {
        if (instance == null) {
            instance = new TeacherGetScheduleRepository();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public LiveData<Response<TeacherScheduleResponse>> getTeacherSchedule(final Application application) {
        setBearerToken(application);
        final MutableLiveData<Response<TeacherScheduleResponse>> data = new MutableLiveData<>();
        GetApiInterfaces.getInstance().getApiInterfaces(application).getTeacherSchedule(ConfigurationFile.Constants.API_KEY,bearerToken,
                ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.ACCEPT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data::setValue);
        return data;
    }

    private void setBearerToken(Application application) {
        CustomUtils customUtils=new CustomUtils(application);
        this.bearerToken = ConfigurationFile.Constants.BEARER+customUtils.getSavedTeacherData().getTeacherData().getApiToken();
    }
}
