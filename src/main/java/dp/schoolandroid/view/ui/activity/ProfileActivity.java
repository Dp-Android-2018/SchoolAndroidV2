package dp.schoolandroid.view.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.databinding.ActivityProfileBinding;

/*
 * this class is responsible for initialize the profile activity
 * */
public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        setUiData();
        initializePasswordConstraintLayout();
    }

    private void setUiData() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getApplication()));
        binding.nameTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getName());
        binding.phoneNumberTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getPhone());
        binding.addressTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getAddress());
        binding.birthdayTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getBirthDate());
        binding.emailTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getEmail());
        binding.nationalityTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getNationality());
        binding.genderTextView.setText(customUtils.getSavedTeacherData().getTeacherData().getGender());
        ImageView teacherPhoto = binding.ivStudentPhoto;
        Picasso.get().load(customUtils.getSavedTeacherData().getTeacherData().getImage()).into(teacherPhoto);
    }

    private void initializePasswordConstraintLayout() {
        binding.passwordConstraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditPasswordActivity.class);
            startActivity(intent);
        });
    }
}
