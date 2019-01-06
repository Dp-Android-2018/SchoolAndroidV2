package dp.schoolandroid.view.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityConnectionErrorBinding;
import dp.schoolandroid.databinding.FragmentBaseWithDataBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.activity.ConnectionErrorActivity;
import dp.schoolandroid.view.ui.activity.HomeActivity;
import dp.schoolandroid.view.ui.adapter.ClassRecyclerViewAdapter;
import dp.schoolandroid.view.ui.adapter.NewsFeedRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.BaseFragmentWithDataViewModel;

/*
* this class is responsible for setting up the base fragment of home activity
* set data into it
* set the picture  image
* */
public class BaseFragmentWithData extends Fragment {
    FragmentBaseWithDataBinding binding;
    ClassRecyclerViewAdapter classRecyclerViewAdapter;

    @Inject
    public BaseFragmentWithData() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_with_data, container, false);
            setTeacherData();
            setupToolbar();
            setupCollapsingToolbarTitle();
            return binding.getRoot();
    }

    private void setupToolbar() {
        binding.baseFragmentToolbar.setNavigationIcon(R.drawable.ic_action_menu);
        binding.baseFragmentToolbar.setNavigationOnClickListener(v -> HomeActivity.drawer.openDrawer(GravityCompat.START));
    }

    private void setupCollapsingToolbarTitle() {
        binding.collapsingToolbar.setTitle(binding.tvTeacherName.getText().toString());
        binding.collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        binding.collapsingToolbar.setCollapsedTitleGravity(View.TEXT_ALIGNMENT_CENTER);
        binding.collapsingToolbar.setExpandedTitleColor(View.INVISIBLE);
    }

    private void setTeacherData() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getActivity()).getApplication());
        binding.tvTeacherName.setText(customUtils.getSavedTeacherData().getTeacherData().getName());
        binding.tvTeacherEmail.setText(customUtils.getSavedTeacherData().getTeacherData().getEmail());
        ImageView teacherPhoto = binding.rivTeacherPhoto;
        Picasso.get().load(customUtils.getSavedTeacherData().getTeacherData().getImage()).into(teacherPhoto);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedUtils.getInstance().showProgressDialog(getContext());
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
        classRecyclerViewAdapter = new ClassRecyclerViewAdapter(feedModels);
        binding.baseClassRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        binding.baseClassRecyclerView.setAdapter(classRecyclerViewAdapter);
    }
}
