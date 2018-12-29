package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityStudentsBinding;

public class StudentsActivity extends AppCompatActivity {
    ActivityStudentsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_students);
    }
}
