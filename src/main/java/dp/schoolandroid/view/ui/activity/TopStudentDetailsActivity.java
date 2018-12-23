package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import dp.schoolandroid.R;
import dp.schoolandroid.databinding.ActivityAssignementDescribtionLayoutBinding;
import dp.schoolandroid.viewmodel.TopStudentDetailsActivityViewModel;

public class TopStudentDetailsActivity extends AppCompatActivity {
    ActivityAssignementDescribtionLayoutBinding binding;
    TopStudentDetailsActivityViewModel viewModel;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniyializeUi();
    }

    private void iniyializeUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_assignement_describtion_layout);
        viewModel = ViewModelProviders.of(this).get(TopStudentDetailsActivityViewModel.class);
        binding.setViewModel(viewModel);
        btn_send = binding.btnSend;
        btn_send.setOnClickListener(v -> {
            Intent intent = new Intent(TopStudentDetailsActivity.this, FeedDetailsActivity.class);
            startActivity(intent);
        });
    }

}
