package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

public class SessionHistoryDetailsActivityViewModel extends AndroidViewModel {
    private Application application;

    public SessionHistoryDetailsActivityViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }
}
