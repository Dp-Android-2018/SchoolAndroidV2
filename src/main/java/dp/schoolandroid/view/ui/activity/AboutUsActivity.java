package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.databinding.FragmentAboutUsBinding;
import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.viewmodel.AboutUsActivityViewModel;
import dp.schoolandroid.viewmodel.ContactUsActivityViewModel;
import retrofit2.Response;


public class AboutUsActivity extends AppCompatActivity {
    FragmentAboutUsBinding binding;
    private AboutUsActivityViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_about_us);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupViewModel();
        setupToolbar();
    }

    private void setupViewModel() {
        SharedUtils.getInstance().showProgressDialog(this);
        viewModel = ViewModelProviders.of(this).get(AboutUsActivityViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getData().observe(this, new Observer<Response<AboutUsResponse>>() {
            @Override
            public void onChanged(@Nullable Response<AboutUsResponse> aboutUsResponseResponse) {
                if (aboutUsResponseResponse != null) {
                    if (aboutUsResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                        SharedUtils.getInstance().cancelDialog();
                        if (aboutUsResponseResponse.body() != null) {
                            initializeUiWithData(aboutUsResponseResponse.body().getAboutUsInfoResponseModel());
                        }
                    } else if (aboutUsResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                        SharedUtils.getInstance().cancelDialog();
                        logout();
                    }
                }
            }
        });
    }

    private void initializeUiWithData(String aboutUsInfoResponseModel) {
        binding.tvAboutUsText.setText(aboutUsInfoResponseModel);
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

    private void setupToolbar() {
        binding.aboutusToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.aboutusToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }
}
