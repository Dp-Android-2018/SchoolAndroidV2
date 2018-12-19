package dp.schoolandroid.viewmodel;

import android.app.ActivityOptions;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
