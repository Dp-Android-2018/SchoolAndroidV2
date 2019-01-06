package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.databinding.FragmentPictureGalleryBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.service.model.global.LinksModel;
import dp.schoolandroid.service.model.global.MetaDataModel;
import dp.schoolandroid.service.model.response.GalleryResponse;
import dp.schoolandroid.service.repository.remotes.PictureGalleryRepository;
import dp.schoolandroid.view.ui.adapter.NewsFeedRecyclerViewAdapter;
import dp.schoolandroid.view.ui.adapter.PictureGalleryRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.NewsFeedFragmentViewModel;
import dp.schoolandroid.viewmodel.PictureGalleryViewModel;
import retrofit2.Response;


public class PictureGalleryActivity extends AppCompatActivity {

    FragmentPictureGalleryBinding binding;
    PictureGalleryViewModel viewModel;
    private int visibleThreshold = 5;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_picture_gallery);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupToolbar();
        SharedUtils.getInstance().showProgressDialog(this);
        viewModel = ViewModelProviders.of(this).get(PictureGalleryViewModel.class);
        viewModel.executeGetImages(1);
        observeViewModel(viewModel);
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
                    if (galleryResponseResponse.body() != null) {
                        ArrayList<String> pageImages = galleryResponseResponse.body().getPageImages();
                        MetaDataModel metaData = galleryResponseResponse.body().getMetaData();
                        initializeRecyclerViewAdapter(pageImages,metaData);
                    } else {
                        Snackbar.make(binding.getRoot(), R.string.no_classes, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), R.string.error_code + galleryResponseResponse.code(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeRecyclerViewAdapter(ArrayList<String> pageImages,MetaDataModel metaData) {
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        binding.rvPicture.setLayoutManager(mGridLayoutManager);
        PictureGalleryRecyclerViewAdapter pictureGalleryRecyclerViewAdapter = new PictureGalleryRecyclerViewAdapter(pageImages);
        binding.rvPicture.setAdapter(pictureGalleryRecyclerViewAdapter);
        makeScrollListenerOnRecyclerView(metaData,pageImages);
    }

    private void makeScrollListenerOnRecyclerView(MetaDataModel metaData, ArrayList<String> pageImages) {
        binding.rvPicture.addOnScrollListener(PictureGalleryRepository.getInstance().onScrollListener(getApplication(),metaData,pageImages));
    }
}
