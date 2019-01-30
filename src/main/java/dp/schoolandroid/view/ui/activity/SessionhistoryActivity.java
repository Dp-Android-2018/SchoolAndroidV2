package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivitySessionhistoryBinding;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.service.model.global.SessionHistoryResponseModel;
import dp.schoolandroid.service.model.response.teacherresponse.SessionHistoryResponse;
import dp.schoolandroid.view.ui.adapter.DayRecyclerViewAdapter;
import dp.schoolandroid.view.ui.adapter.SessionHistoryRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.ClassViewModel;
import dp.schoolandroid.viewmodel.SessionHistoryActivityViewModel;
import retrofit2.Response;

public class SessionhistoryActivity extends AppCompatActivity {

    ActivitySessionhistoryBinding binding;
    SessionHistoryActivityViewModel viewModel;
    private SectionTimeModel sectionTimeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_sessionhistory);
        sectionTimeModel = getIntent().getParcelableExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL);
        setupToolbar();
        setupViewModel();
    }

    private void setupToolbar() {
        binding.sessionHistoryToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.sessionHistoryToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupViewModel() {
        if (ValidationUtils.isConnectingToInternet(this)) {
            SharedUtils.getInstance().showProgressDialog(this);
            viewModel = ViewModelProviders.of(this).get(SessionHistoryActivityViewModel.class);
            viewModel.executeGetSessionsForSession(Integer.parseInt(sectionTimeModel.getArrayId()));
            observeViewModel(viewModel);
        }else {
            Intent intent=new Intent(this,ConnectionErrorActivity.class);
            startActivity(intent);
        }
    }

    private void observeViewModel(SessionHistoryActivityViewModel viewModel) {
        viewModel.getData().observe(this, sessionHistoryResponseResponse -> {
            if (sessionHistoryResponseResponse != null) {
                if (sessionHistoryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE||
                sessionHistoryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE_SECOND) {
                    SharedUtils.getInstance().cancelDialog();
                    if (sessionHistoryResponseResponse.body() != null) {
                        setupSessionRecyclerView(sessionHistoryResponseResponse.body().getSessionHistoryResponseModel());
                    }
                } else if (sessionHistoryResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void setupSessionRecyclerView(ArrayList<SessionHistoryResponseModel> body) {
        SessionHistoryRecyclerViewAdapter sessionHistoryRecyclerViewAdapter = new SessionHistoryRecyclerViewAdapter(body,sectionTimeModel);
        binding.sessionHistoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        binding.sessionHistoryRv.setAdapter(sessionHistoryRecyclerViewAdapter);
    }

    private void logout() {
        clearSharedPreferences();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(this.getApplication());
        customUtils.clearSharedPref();
    }
}
