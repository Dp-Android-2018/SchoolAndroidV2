package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityCalendarBinding;
import dp.schoolandroid.di.component.NetworkComponent;
import dp.schoolandroid.viewmodel.CalendarActivityViewModel;

public class CalendarActivity extends AppCompatActivity {

    CalendarActivityViewModel viewModel;
    ActivityCalendarBinding binding;
    NetworkComponent networkComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        viewModel = ViewModelProviders.of(this).get(CalendarActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}
