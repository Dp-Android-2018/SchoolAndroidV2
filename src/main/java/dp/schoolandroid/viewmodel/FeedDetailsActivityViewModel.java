package dp.schoolandroid.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import dp.schoolandroid.service.model.global.FeedModel;

public class FeedDetailsActivityViewModel {
    public FeedModel feedModel;

    public FeedDetailsActivityViewModel(FeedModel feedModel) {
        this.feedModel = feedModel;
    }

}
