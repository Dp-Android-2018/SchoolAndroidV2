package dp.schoolandroid.view.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityProfileBinding;

/*
 * this class is responsible for initialize the profile activity
 * */
public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.passwordConstraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditPasswordActivity.class);
            startActivity(intent);
        });
    }
}
