package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.FragmentSuggestionBinding;
import dp.schoolandroid.service.model.response.AboutUsResponse;
import dp.schoolandroid.viewmodel.SuggestionActivityViewModel;
import retrofit2.Response;


public class SuggestionActivity extends AppCompatActivity {

    FragmentSuggestionBinding binding;
    SuggestionActivityViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.fragment_suggestion);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupToolbar();
        setupViewModel();
    }

    private void setupViewModel() {
        viewModel=ViewModelProviders.of(this).get(SuggestionActivityViewModel.class);
    }

    private void setupToolbar() {
        binding.suggestionToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.suggestionToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    public void sndSuggestion(View view) {
        if ( !(TextUtils.isEmpty(binding.suggestionTitle.getText().toString()) && TextUtils.isEmpty(binding.suggestionDescribtion.getText().toString()))) {
            SharedUtils.getInstance().showProgressDialog(this);
            viewModel.handleSuggestionRequest(binding.suggestionTitle.getText().toString(), binding.suggestionDescribtion.getText().toString());
            observeCheckViewModel(viewModel);
        } else {
            Snackbar.make(binding.getRoot(), R.string.error_title_or_describtion, Toast.LENGTH_SHORT).show();
        }
    }

    private void observeCheckViewModel(SuggestionActivityViewModel viewModel) {
        viewModel.getData().observe(this, aboutUsResponseResponse -> {
            if (aboutUsResponseResponse != null) {
                if (aboutUsResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (aboutUsResponseResponse.body() != null) {
                        binding.suggestionTitle.getText().clear();
                        binding.suggestionDescribtion.getText().clear();
                        Snackbar.make(binding.getRoot(), aboutUsResponseResponse.body().getSuggestionResponseModel(), Toast.LENGTH_SHORT).show();
                    }
                } else if (aboutUsResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }else {
                    Snackbar.make(binding.getRoot(), R.string.error_code+aboutUsResponseResponse.code(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
