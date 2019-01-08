package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.GridSpacingItemDecoration;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.databinding.FragmentPictureGalleryBinding;
import dp.schoolandroid.databinding.FragmentVideosBinding;
import dp.schoolandroid.service.model.global.VideosModel;
import dp.schoolandroid.view.ui.adapter.PictureGalleryRecyclerViewAdapter;
import dp.schoolandroid.view.ui.adapter.VideosRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.PictureGalleryViewModel;
import dp.schoolandroid.viewmodel.VideosActivityViewModel;


public class VideosActivity extends AppCompatActivity {

    FragmentVideosBinding binding;
    VideosActivityViewModel viewModel;
    private String pageId = ConfigurationFile.Constants.PAGE_ID;
    VideosRecyclerViewAdapter videosRecyclerViewAdapter;
    private List<VideosModel> pageVideosList;
    private String nextPageUrl = null;
    private boolean isLoading = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.fragment_videos);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupToolbar();
        initVariables();
        SharedUtils.getInstance().showProgressDialog(this);
        viewModel = ViewModelProviders.of(this).get(VideosActivityViewModel.class);
        viewModel.executeGetVideos(1);
        observeViewModel(viewModel);
        initializeRecyclerViewAdapter();
    }

    public void initVariables() {
        pageVideosList = new ArrayList<>();
        videosRecyclerViewAdapter = new VideosRecyclerViewAdapter();
    }
    private void setupToolbar() {
        binding.videosToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.videosToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void observeViewModel(VideosActivityViewModel viewModel) {
        viewModel.getData().observe(this, videosResponseResponse -> {
            if (videosResponseResponse != null) {
                if (videosResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (pageId.equals(ConfigurationFile.Constants.PAGE_ID)) {
                        resetRecyclerViewAdapter();
                    }
                    if (videosResponseResponse.body() != null) {
                        if (!TextUtils.isEmpty(videosResponseResponse.body().getPageLinks().getNextPageLink())) {
                            nextPageUrl = videosResponseResponse.body().getPageLinks().getNextPageLink();
                            pageId = nextPageUrl.substring(nextPageUrl.length() - 1);
                        } else {
                            nextPageUrl = null;
                        }
                    }
                    isLoading = false;
                    if (videosResponseResponse.body() != null) {
                        pageVideosList.addAll(videosResponseResponse.body().getPageVideos());
                    }
                    videosRecyclerViewAdapter.notifyDataSetChanged();
                }else if (videosResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE){
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void logout() {
        clearSharedPreferences();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(this.getApplication());
        customUtils.clearSharedPref();
    }
    private void resetRecyclerViewAdapter() {
        pageVideosList.clear();
        videosRecyclerViewAdapter.setPageVideos(pageVideosList);
        binding.rvVideos.setAdapter(videosRecyclerViewAdapter);
    }

    private void initializeRecyclerViewAdapter() {
        int spanCount = 3;
        int spacing =16;
        binding.rvVideos.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));
        binding.rvVideos.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvVideos.addOnScrollListener(onScrollListener());
        videosRecyclerViewAdapter.setPageVideos(pageVideosList);
        binding.rvVideos.setAdapter(videosRecyclerViewAdapter);
    }

    public RecyclerView.OnScrollListener onScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == (pageVideosList.size() - 1)) {
                    if (!TextUtils.isEmpty(nextPageUrl) && !isLoading) {
                        isLoading = true;
                        viewModel.executeGetVideos(Integer.parseInt(pageId));
                        observeViewModel(viewModel);
                    }
                }
            }
        };
    }
}
