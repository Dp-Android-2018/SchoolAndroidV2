package dp.schoolandroid.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityConnectionErrorBinding;

/*
 * this class is shown when Error Connection happen
 */
public class ConnectionErrorActivity extends AppCompatActivity {

    ActivityConnectionErrorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_connection_error);
    }
}
