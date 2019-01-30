package dp.schoolandroid.view.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityAssignementDescribtionLayoutBinding;
import dp.schoolandroid.databinding.ActivityTopStudentDetailsBinding;

public class TopStudentDetailsActivity extends AppCompatActivity {
    ActivityTopStudentDetailsBinding binding;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniyializeUi();
    }

    private void iniyializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_student_details);
    }

}
