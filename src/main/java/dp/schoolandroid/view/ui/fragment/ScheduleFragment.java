package dp.schoolandroid.view.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import javax.inject.Inject;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.databinding.FragmentScheduleBinding;
import dp.schoolandroid.service.model.global.TeacherSchedule;
import dp.schoolandroid.view.ui.activity.HomeActivity;
import dp.schoolandroid.view.ui.activity.MainActivity;
import dp.schoolandroid.view.ui.adapter.TeacherSchedulePageViewAdapter;
import dp.schoolandroid.viewmodel.MyCustomBarViewModel;
import dp.schoolandroid.viewmodel.ScheduleFragmentViewModel;


public class ScheduleFragment extends Fragment {
    private final int PAGE_lIMIT = 1;
    FragmentScheduleBinding binding;

    @Inject
    public ScheduleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        setupToolbar();
        return binding.getRoot();
    }

    private void setupToolbar() {
        binding.scheduleFragmentToolbar.setNavigationIcon(R.drawable.ic_action_menu);
        binding.scheduleFragmentToolbar.setNavigationOnClickListener(v -> HomeActivity.drawer.openDrawer(GravityCompat.START));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedUtils.getInstance().showProgressDialog(getContext());
        final ScheduleFragmentViewModel viewModel = ViewModelProviders.of(this).get(ScheduleFragmentViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(ScheduleFragmentViewModel viewModel) {
        viewModel.getData().observe(this, teacherScheduleResponseResponse -> {
            if (teacherScheduleResponseResponse != null) {
                if (teacherScheduleResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (teacherScheduleResponseResponse.body() != null) {
                        TeacherSchedule weekData = teacherScheduleResponseResponse.body().getTeacherScheduleData();
                        initializeViewPager(weekData);
                    } else {
                        Snackbar.make(binding.scheduleFragmentConstraintLayout, getString(R.string.no_week_data), Snackbar.LENGTH_SHORT).show();
                    }
                } else if (teacherScheduleResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE){
                    SharedUtils.getInstance().cancelDialog();
                    logout();
                }
            }
        });

    }

    private void logout() {
        clearSharedPreferences();
        Intent intent=new Intent(getContext(),MainActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void clearSharedPreferences() {
        CustomUtils customUtils = new CustomUtils(Objects.requireNonNull(getActivity()).getApplication());
        customUtils.clearSharedPref();
    }

    public void initializeViewPager(TeacherSchedule weekData) {
        ViewPager viewPager = binding.viewpagerSchedule;
        viewPager.setOffscreenPageLimit(PAGE_lIMIT);
        TeacherSchedulePageViewAdapter pageViewAdapter = new TeacherSchedulePageViewAdapter(getFragmentManager(), weekData);
        viewPager.setAdapter(pageViewAdapter);
        TabLayout tabLayout = binding.tlScheduleClass;
        tabLayout.setupWithViewPager(viewPager);
    }
}
