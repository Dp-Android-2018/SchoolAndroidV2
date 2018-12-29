package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityCalendarBinding;
import dp.schoolandroid.di.component.NetworkComponent;

public class CalendarActivity extends AppCompatActivity {

    ActivityCalendarBinding binding;
    NetworkComponent networkComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
    }
}
