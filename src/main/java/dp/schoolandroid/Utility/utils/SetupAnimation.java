package dp.schoolandroid.Utility.utils;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.view.Window;

import dp.schoolandroid.R;

/*
 * this class is to setup animation to activities
 * */
public class SetupAnimation {
    private static SetupAnimation instance;

    public static SetupAnimation getInstance() {
        if (instance == null) {
            instance = new SetupAnimation();
        }
        return instance;
    }

    //the function to setup animation
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setUpAnimation(Window window, Resources resources) {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(resources.getInteger(R.integer.anim_duration_very_long));
        window.setEnterTransition(enterTransition);
    }

}
