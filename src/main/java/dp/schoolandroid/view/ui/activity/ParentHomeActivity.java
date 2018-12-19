package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityParentHomeBinding;
import dp.schoolandroid.viewmodel.ParentHomeActivityViewModel;

public class ParentHomeActivity extends AppCompatActivity {

    ParentHomeActivityViewModel viewModel;
    ActivityParentHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_parent_home);
        viewModel=ViewModelProviders.of(this).get(ParentHomeActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}
