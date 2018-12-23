package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityStudentsBinding;
import dp.schoolandroid.viewmodel.StudentActivityViewModel;

public class StudentsActivity extends AppCompatActivity {
    ActivityStudentsBinding binding;
    StudentActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_students);
        viewModel = ViewModelProviders.of(this).get(StudentActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}
