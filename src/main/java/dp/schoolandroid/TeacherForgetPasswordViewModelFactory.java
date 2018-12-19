package dp.schoolandroid;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import dp.schoolandroid.viewmodel.TeacherLoginActivityViewModel;

public class TeacherForgetPasswordViewModelFactory implements ViewModelProvider.Factory {
    private Application mapplication;
    private String mphone;

    public TeacherForgetPasswordViewModelFactory(Application application, String phone) {
        this.mapplication = application;
        this.mphone = phone;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }

   /* @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TeacherLoginActivityViewModel(mapplication, mphone,"12",2);
    }*/
}
