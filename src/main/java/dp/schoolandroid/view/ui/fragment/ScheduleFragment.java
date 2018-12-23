package dp.schoolandroid.view.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.databinding.FragmentScheduleBinding;
import dp.schoolandroid.service.model.global.TeacherSchedule;
import dp.schoolandroid.view.ui.adapter.TeacherSchedulePageViewAdapter;
import dp.schoolandroid.viewmodel.MyCustomBarViewModel;
import dp.schoolandroid.viewmodel.ScheduleFragmentViewModel;


public class ScheduleFragment extends Fragment {
    private final int PAGE_lIMIT = 1;
    FragmentScheduleBinding binding;

    public ScheduleFragment() {
    }

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        initUi();
        return binding.getRoot();
    }

    private void initUi() {
        binding.actionBar.setViewModel(new MyCustomBarViewModel(getContext()));
        binding.actionBar.tvActionBarTitle.setText(R.string.schedule);
        binding.actionBar.chatMenuImage.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ScheduleFragmentViewModel viewModel = ViewModelProviders.of(this).get(ScheduleFragmentViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(ScheduleFragmentViewModel viewModel) {
        viewModel.getData().observe(this, teacherScheduleResponseResponse -> {
            if (teacherScheduleResponseResponse != null && teacherScheduleResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                TeacherSchedule weekData = null;
                if (teacherScheduleResponseResponse.body() != null) {
                    weekData = teacherScheduleResponseResponse.body().getData();
                }
                initializeViewPager(weekData);
            }
        });

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
