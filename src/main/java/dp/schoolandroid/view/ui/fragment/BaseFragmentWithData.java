package dp.schoolandroid.view.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Objects;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.databinding.FragmentBaseWithDataBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.activity.HomeActivity;
import dp.schoolandroid.view.ui.adapter.NewsFeedRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.BaseFragmentWithDataViewModel;


public class BaseFragmentWithData extends Fragment {
    FragmentBaseWithDataBinding binding;
    NewsFeedRecyclerViewAdapter classRecyclerViewAdapter;

    public static BaseFragmentWithData newInstance() {
        return new BaseFragmentWithData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_with_data, container, false);
        setTeacherImage();
        binding.actionMenuImage.setOnClickListener(v -> HomeActivity.drawer.openDrawer(GravityCompat.START));
        return binding.getRoot();
    }

    private void setTeacherImage() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getActivity()).getApplication());
        ImageView teacherPhoto = binding.rivTeacherPhoto;
        Picasso.get().load(customUtils.getSavedTeacherData().getTeacherData().getImage()).into(teacherPhoto);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final BaseFragmentWithDataViewModel viewModel = ViewModelProviders.of(this).get(BaseFragmentWithDataViewModel.class);
        observeViewModel(viewModel);

    }

    private void observeViewModel(BaseFragmentWithDataViewModel viewModel) {
        viewModel.getData().observe(this, feedsResponseResponse -> {
            if (feedsResponseResponse != null) {
                if (feedsResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    if (feedsResponseResponse.body() != null) {
                        ArrayList<FeedModel> feedModels = feedsResponseResponse.body().getNewsFeedResponseData();
                        initializeRecyclerViewAdapter(feedModels);
                    } else {
                        Snackbar.make(binding.getRoot(), R.string.no_classes, Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), R.string.error_code + feedsResponseResponse.code(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeRecyclerViewAdapter(ArrayList<FeedModel> feedModels) {
        classRecyclerViewAdapter = new NewsFeedRecyclerViewAdapter(feedModels);
        binding.baseClassRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        binding.baseClassRecyclerView.setAdapter(classRecyclerViewAdapter);
    }
}
