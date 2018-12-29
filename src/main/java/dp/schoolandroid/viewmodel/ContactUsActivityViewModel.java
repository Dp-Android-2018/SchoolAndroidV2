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
    private final LiveData<Response<ContactUsResponse>> data;


    public ContactUsActivityViewModel(@NonNull Application application) {
        super(application);
        data = ContactUsRepository.getInstance().getContactInfoForTeacher(application);
    }

    public LiveData<Response<ContactUsResponse>> getData() {
        return data;
    }
}
