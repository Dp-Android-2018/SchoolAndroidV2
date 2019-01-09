package dp.schoolandroid.view.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.databinding.ActivityProfileBinding;

/*
 * this class is responsible for initialize the profile activity
 * */
public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    private String memberType;
    private CustomUtils customUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        memberType=getIntent().getStringExtra(ConfigurationFile.Constants.MEMBER_Key);
        setupToolbar();
        setUiData();
        initializePasswordConstraintLayout();
    }

    private void setupToolbar() {
        binding.profileToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.profileToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setUiData() {
        customUtils = new CustomUtils(Objects.requireNonNull(getApplication()));
        if (memberType.equals(ConfigurationFile.Constants.TEACHER_Key_VALUE)){
            setTeacherData();
        }else if (memberType.equals(ConfigurationFile.Constants.STUDENT_Key_VALUE)){
            setStudentData();
        }
    }

    private void setTeacherData() {
        binding.nameTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getName());
        binding.phoneNumberTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getPhone());
        binding.addressTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getAddress());
        binding.birthdayTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getTeacherBirthDate());
        binding.emailTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getEmail());
        binding.nationalityTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getNationality());
        binding.genderTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getGender());
        ImageView teacherPhoto = binding.ivStudentPhoto;
        Picasso.get().load(customUtils.getSavedTeacherData().getTeacherData().getImage()).into(teacherPhoto);
    }

    private void setStudentData() {
        binding.nameTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getName());
        binding.phoneNumberTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getPhone());
        binding.addressTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getAddress());
        binding.birthdayTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getStudentBirthDate());
        binding.emailTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getEmail());
        binding.nationalityTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getNationality());
        binding.genderTextView.setText(customUtils.getSavedStudentData().getStudentResponseData().getGender());
        ImageView teacherPhoto = binding.ivStudentPhoto;
        Picasso.get().load(customUtils.getSavedStudentData().getStudentResponseData().getImage()).into(teacherPhoto);
    }

    private void initializePasswordConstraintLayout() {
        binding.passwordConstraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditPasswordActivity.class);
            intent.putExtra(ConfigurationFile.Constants.MEMBER_Key,memberType);
            startActivity(intent);
        });
    }
}
