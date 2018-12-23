package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.ActivityForgetPasswordBinding;
import dp.schoolandroid.viewmodel.ForgetPasswordViewModel;

/*
 * this class is responsible for Forget Password Activity
 * */
public class ForgetPasswordActivity extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    ForgetPasswordViewModel viewModel;
    int membershipType;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        initializeUi();
    }

    private void initializeUi() {
        membershipType = getIntent().getIntExtra(ConfigurationFile.Constants.ACTIVITY_NUMBER, 0);
        phoneNumber = null;
        viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel.class);
        viewModel.setType(membershipType);
        initializeViewModel();
    }

    private void initializeViewModel() {
        switch (membershipType) {
            case ConfigurationFile.Constants.TEACHER_ACTIVITY_CODE:
                activtyTypeIsTeacher();
                break;
            case ConfigurationFile.Constants.PARENT_ACTIVITY_CODE:
                activtyTypeIsParent();
                break;
            case ConfigurationFile.Constants.STUDENT_ACTIVITY_CODE:
                activtyTypeIsStudent();
                break;
        }
        viewModel.setPhoneNumber(phoneNumber);
        binding.setViewModel(viewModel);
    }

    private void activtyTypeIsStudent() {
        phoneNumber = getIntent().getStringExtra(ConfigurationFile.Constants.STUDENT_PHONE_NUMBER);
        binding.numberTextView.setText(phoneNumber);
        binding.forgetPasswordImageView.setImageResource(R.drawable.img_checked_student);
    }

    private void activtyTypeIsParent() {
        phoneNumber = getIntent().getStringExtra(ConfigurationFile.Constants.PARENT_PHONE_NUMBER);
        binding.numberTextView.setText(phoneNumber);
        binding.forgetPasswordImageView.setImageResource(R.drawable.img_checked_parent);
    }

    private void activtyTypeIsTeacher() {
        phoneNumber = getIntent().getStringExtra(ConfigurationFile.Constants.TEACHER_PHONE_NUMBER);
        binding.numberTextView.setText(phoneNumber);
    }
}
