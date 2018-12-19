package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityProfileBinding;
import dp.schoolandroid.viewmodel.ProfileActivityViewModel;

/*
 * this class is responsible for initialize the profile activity
 * */
public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    ProfileActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        viewModel = ViewModelProviders.of(this).get(ProfileActivityViewModel.class);
        binding.setViewModel(viewModel);
        binding.passwordConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
