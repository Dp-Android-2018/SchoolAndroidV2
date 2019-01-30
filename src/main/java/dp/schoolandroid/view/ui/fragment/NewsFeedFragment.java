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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.FragmentNewsFeedBinding;
import dp.schoolandroid.service.model.global.FeedModel;
import dp.schoolandroid.view.ui.activity.ConnectionErrorActivity;
import dp.schoolandroid.view.ui.activity.MainActivity;
import dp.schoolandroid.view.ui.activity.TeacherHomeActivity;
import dp.schoolandroid.view.ui.adapter.NewsFeedRecyclerViewAdapter;
import dp.schoolandroid.viewmodel.NewsFeedFragmentViewModel;


public class NewsFeedFragment extends Fragment {
    FragmentNewsFeedBinding binding;
    private String memberType;

    @Inject
    public NewsFeedFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_feed, container, false);
        memberType = Objects.requireNonNull(getArguments()).getString(ConfigurationFile.Constants.MEMBER_Key);
        setupToolbar();
        return binding.getRoot();
    }

    private void setupToolbar() {
        binding.teacherNewsFeedToolbar.setNavigationIcon(R.drawable.ic_action_menu);
        binding.teacherNewsFeedToolbar.setNavigationOnClickListener(v -> TeacherHomeActivity.drawer.openDrawer(GravityCompat.START));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (ValidationUtils.isConnectingToInternet(Objects.requireNonNull(getContext()))) {
            chooseBetweenMemberTypes();
        } else {
            Intent intent = new Intent(getContext(), ConnectionErrorActivity.class);
            startActivity(intent);
        }
    }

    private void chooseBetweenMemberTypes() {
        if (memberType.equals(ConfigurationFile.Constants.TEACHER_Key_VALUE)) {
            SharedUtils.getInstance().showProgressDialog(getContext());
            NewsFeedFragmentViewModel viewModel = ViewModelProviders.of(this).get(NewsFeedFragmentViewModel.class);
            viewModel.handleGetTeacherNewsFeed();
            observeViewModel(viewModel);
        }else if (memberType.equals(ConfigurationFile.Constants.STUDENT_Key_VALUE)){
            SharedUtils.getInstance().showProgressDialog(getContext());
            NewsFeedFragmentViewModel viewModel = ViewModelProviders.of(this).get(NewsFeedFragmentViewModel.class);
            viewModel.handleGetStudentNewsFeed();
            observeViewModel(viewModel);
        }
    }

    private void observeViewModel(NewsFeedFragmentViewModel viewModel) {
        viewModel.getData().observe(this, feedsResponseResponse -> {
            if (feedsResponseResponse != null) {
                if (feedsResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (feedsResponseResponse.body() != null) {
                        ArrayList<FeedModel> feedModels = feedsResponseResponse.body().getNewsFeedResponseData();
                        initializeRecyclerViewAdapter(feedModels);
                    } else {
                        Snackbar.make(binding.getRoot(), R.string.no_classes, Snackbar.LENGTH_SHORT).show();
                    }
                } else if (feedsResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });
    }

    private void logout() {
        clearSharedPreferences();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getActivity()).getApplication());
        customUtils.clearSharedPref();
    }

    private void initializeRecyclerViewAdapter(ArrayList<FeedModel> feedModels) {
        NewsFeedRecyclerViewAdapter newsFeedRecyclerViewAdapter = new NewsFeedRecyclerViewAdapter(feedModels);
        binding.teacherNewsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        binding.teacherNewsFeedRecyclerView.setAdapter(newsFeedRecyclerViewAdapter);
    }

}
