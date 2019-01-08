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
import dp.schoolandroid.view.ui.adapter.PictureGalleryRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.PictureGalleryViewModel;


public class PictureGalleryActivity extends AppCompatActivity {

    FragmentPictureGalleryBinding binding;
    PictureGalleryViewModel viewModel;
    private String pageId = ConfigurationFile.Constants.PAGE_ID;
    PictureGalleryRecyclerViewAdapter pictureGalleryRecyclerViewAdapter;
    private List<String> pageImagesList;
    private String nextPageUrl = null;
    private boolean isLoading = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_picture_gallery);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupToolbar();
        initVariables();
        SharedUtils.getInstance().showProgressDialog(this);
        viewModel = ViewModelProviders.of(this).get(PictureGalleryViewModel.class);
        viewModel.executeGetImages(1);
        observeViewModel(viewModel);
        initializeRecyclerViewAdapter();
    }

    public void initVariables() {
        pageImagesList = new ArrayList<>();
        pictureGalleryRecyclerViewAdapter = new PictureGalleryRecyclerViewAdapter();
    }

    private void setupToolbar() {
        binding.pictureGalleryToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.pictureGalleryToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void observeViewModel(PictureGalleryViewModel viewModel) {
        viewModel.getData().observe(this, galleryResponseResponse -> {
            if (galleryResponseResponse != null) {
                if (galleryResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (pageId.equals(ConfigurationFile.Constants.PAGE_ID)) {
                        resetRecyclerViewAdapter();
                    }
                    if (galleryResponseResponse.body() != null) {
                        if (!TextUtils.isEmpty(galleryResponseResponse.body().getPageLinks().getNextPageLink())) {
                            nextPageUrl = galleryResponseResponse.body().getPageLinks().getNextPageLink();
                            pageId = nextPageUrl.substring(nextPageUrl.length() - 1);
                        } else {
                            nextPageUrl = null;
                        }
                    }
                    isLoading = false;
                    if (galleryResponseResponse.body() != null) {
                        pageImagesList.addAll(galleryResponseResponse.body().getPageImages());
                    }
                    pictureGalleryRecyclerViewAdapter.notifyDataSetChanged();
                }else if (galleryResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE){
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
        pageImagesList.clear();
        pictureGalleryRecyclerViewAdapter.setPageImages(pageImagesList);
        binding.rvPicture.setAdapter(pictureGalleryRecyclerViewAdapter);
    }

    private void initializeRecyclerViewAdapter() {
        int spanCount = 3;
        int spacing =16;
        binding.rvPicture.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));
        binding.rvPicture.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvPicture.addOnScrollListener(onScrollListener());
        pictureGalleryRecyclerViewAdapter.setPageImages(pageImagesList);
        binding.rvPicture.setAdapter(pictureGalleryRecyclerViewAdapter);
    }

    public RecyclerView.OnScrollListener onScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == (pageImagesList.size() - 1)) {
                    if (!TextUtils.isEmpty(nextPageUrl) && !isLoading) {
                        isLoading = true;
                        viewModel.executeGetImages(Integer.parseInt(pageId));
                        observeViewModel(viewModel);
                    }
                }
            }
        };
    }
}


