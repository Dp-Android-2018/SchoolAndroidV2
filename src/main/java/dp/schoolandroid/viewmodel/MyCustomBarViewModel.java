package dp.schoolandroid.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.view.View;
import dp.schoolandroid.view.ui.activity.ChatActivity;
import dp.schoolandroid.view.ui.activity.HomeActivity;

public class MyCustomBarViewModel {
    private Context context;

    public MyCustomBarViewModel(Context context) {
        this.context = context;
    }

    public void actionMenuImageClicked(View view) {
        HomeActivity.drawer.openDrawer(GravityCompat.START);
    }

    public void chatMenuImageClicked(View view) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }

}
