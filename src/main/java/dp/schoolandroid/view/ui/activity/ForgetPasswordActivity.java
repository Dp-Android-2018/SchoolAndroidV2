package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityForgetPasswordBinding;
import dp.schoolandroid.viewmodel.ForgetPasswordViewModel;
/*
* this class is responsible for Forget Password Activity
* */
public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    ForgetPasswordViewModel viewModel;
    int activityType;
    String phoneNumber;
    String onThisNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        initializeUi();
    }

    private void initializeUi() {
        activityType = getIntent().getIntExtra("ACTIVITY_NAME", 0);
        phoneNumber = null;
        onThisNumber ="on this number ";
        viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel.class);
        viewModel.setType(activityType);
        initializeViewModel();
    }

    private void initializeViewModel() {
        switch (activityType) {
            case 1:
                activtyTypeIsTeacher();
                break;
            case 2:
                activtyTypeIsParent();
                break;
            case 3:
                activtyTypeIsStudent();
                break;
        }
        viewModel.setPhoneNumber(phoneNumber);
        binding.setViewModel(viewModel);
    }

    private void activtyTypeIsStudent() {
        phoneNumber = getIntent().getStringExtra("SPNUM");
        onThisNumber+=phoneNumber;
        binding.numberTextView.setText(onThisNumber);
        binding.forgetPasswordImageView.setImageResource(R.drawable.img_checked_student);
    }

    private void activtyTypeIsParent() {
        phoneNumber = getIntent().getStringExtra("PPNUM");
        onThisNumber+=phoneNumber;
        binding.numberTextView.setText(onThisNumber);
        binding.forgetPasswordImageView.setImageResource(R.drawable.img_checked_parent);
    }

    private void activtyTypeIsTeacher() {
        phoneNumber = getIntent().getStringExtra("TPNUM");
        onThisNumber+=phoneNumber;
        binding.numberTextView.setText(onThisNumber);
    }
}
