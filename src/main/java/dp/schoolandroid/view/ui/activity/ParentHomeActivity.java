package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityParentHomeBinding;

public class ParentHomeActivity extends AppCompatActivity {

    ActivityParentHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_parent_home);
    }
}
