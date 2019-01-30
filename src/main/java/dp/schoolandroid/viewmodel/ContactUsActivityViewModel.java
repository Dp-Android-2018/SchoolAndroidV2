package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import dp.schoolandroid.service.model.global.ContactInfoResponseModel;
import dp.schoolandroid.service.model.response.ContactUsResponse;
import dp.schoolandroid.service.repository.remotes.ContactUsRepository;
import retrofit2.Response;

public class ContactUsActivityViewModel extends AndroidViewModel {
    private LiveData<Response<ContactUsResponse>> data;
    private Application application;


    public ContactUsActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    public void executeGetTeacherContactInfo(){
        data = ContactUsRepository.getInstance().getContactInfoForTeacher(application);
    }

    public void executeGetStudentContactInfo(){
        data = ContactUsRepository.getInstance().getContactInfoForStudent(application);
    }

    public LiveData<Response<ContactUsResponse>> getData() {
        return data;
    }
}
