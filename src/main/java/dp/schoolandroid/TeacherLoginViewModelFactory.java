package dp.schoolandroid;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;

public class TeacherLoginViewModelFactory implements ViewModelProvider.Factory {
    private Application mapplication;
    private String mphone;
    private String mpassword;

    public TeacherLoginViewModelFactory(Application application, String phone, String password) {
        this.mapplication = application;
        this.mphone = phone;
        this.mpassword = password;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }

    /*@NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TeacherLoginActivityViewModel(mapplication, mphone, mpassword,1);
    }*/
}
