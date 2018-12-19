package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

public class ResetPasswordActivityViewModel extends AndroidViewModel {
    public ObservableField<String> password;
    public ObservableField<String> newPassword;
    private Application application;
    private String apiToken;
    private int type;

    public ResetPasswordActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        initializeUi();
    }

    private void initializeUi() {
        password=new ObservableField<>();
        newPassword=new ObservableField<>();
    }

    public void confirm(View view){
        switch (type){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public void setType(int type) {
        this.type = type;
    }
}
