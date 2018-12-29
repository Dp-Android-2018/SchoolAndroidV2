package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityClassBinding;

public class ClassActivity extends AppCompatActivity {

    ActivityClassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_class);
    }
}
