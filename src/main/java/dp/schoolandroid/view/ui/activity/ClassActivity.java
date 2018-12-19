package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityClassBinding;
import dp.schoolandroid.viewmodel.ClassActivityViewModel;

public class ClassActivity extends AppCompatActivity {

    ClassActivityViewModel viewModel;
    ActivityClassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_class);
        viewModel=ViewModelProviders.of(this).get(ClassActivityViewModel.class);
        binding.setViewModel(viewModel);
    }
}
