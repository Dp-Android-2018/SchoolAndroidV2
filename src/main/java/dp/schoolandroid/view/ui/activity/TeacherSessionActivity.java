package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SharedUtils;
import dp.schoolandroid.Utility.utils.ValidationUtils;
import dp.schoolandroid.databinding.ActivityClassBinding;
import dp.schoolandroid.di.component.DaggerFragmentComponent;
import dp.schoolandroid.di.component.FragmentComponent;
import dp.schoolandroid.service.model.global.SectionTimeModel;
import dp.schoolandroid.view.ui.adapter.ClassViewPagerAdapter;
import dp.schoolandroid.view.ui.fragment.AnnouncementsFragment;
import dp.schoolandroid.view.ui.fragment.StudentAttendanceFragment;
import dp.schoolandroid.view.ui.fragment.StudentQuizFragment;
import dp.schoolandroid.viewmodel.ClassViewModel;

public class TeacherSessionActivity extends AppCompatActivity {

    @Inject
    StudentAttendanceFragment studentAttendanceFragment;
    @Inject
    AnnouncementsFragment announcementsFragment;
    @Inject
    StudentQuizFragment studentQuizFragment;

    ActivityClassBinding binding;
    private LinearLayout linearLayoutOne, linearLayout2, linearLayout3;
    private TextView tvTab1, tvTab2, tvTab3;
    private int prev = 0;
    private TabLayout tabLayout;
    ClassViewModel viewModel;
    private SectionTimeModel sectionTimeModel;
    private int sessionId;
    private int historySessionId;
    private String sessionType;
    ClassViewPagerAdapter classViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_class);
        sectionTimeModel = getIntent().getParcelableExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL);
        sessionType = getIntent().getStringExtra(ConfigurationFile.Constants.SESSIONTYPE);
        historySessionId = getIntent().getIntExtra(ConfigurationFile.Constants.SESSIONID, 0);
        setupDaggerFragmentComponent();
        setupToolbar();
        setUpData();
        initializeVariables();
        setUpViewModel();
        makeActionOnAssignmentButton();
    }

    private void setUpData() {
        binding.levelTextView.setText(sectionTimeModel.getGrade());
        binding.tvClassName.setText(sectionTimeModel.getClassName());
        binding.tvStudentsNum.setText(sectionTimeModel.getStudentsCount());
    }

    private void setUpViewModel() {
        if (sessionType.equals(ConfigurationFile.Constants.NEWSESSION)) {
            if (ValidationUtils.isInternetAvailable()){
                SharedUtils.getInstance().showProgressDialog(this);
                viewModel = ViewModelProviders.of(this).get(ClassViewModel.class);
                viewModel.executeStartNewSession(Objects.requireNonNull(sectionTimeModel).getArrayId());
                observeViewModel(viewModel);
            }else {
                Intent intent=new Intent(this,ConnectionErrorActivity.class);
                startActivity(intent);
            }
        }else if (sessionType.equals(ConfigurationFile.Constants.HISTORYSESSION)){
            initializeClassViewPagerAdapter();
            initializeTabLayout();
        }
    }

    private void setupToolbar() {
        binding.classActivityToolbar.setNavigationIcon(R.drawable.ic_action_back);
        binding.classActivityToolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void setupDaggerFragmentComponent() {
        FragmentComponent component = DaggerFragmentComponent.create();
        component.inject(this);
    }

    private void observeViewModel(ClassViewModel viewModel) {
        viewModel.getData().observe(this, sessionResponseResponse -> {
            if (sessionResponseResponse != null) {
                if (sessionResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    if (sessionResponseResponse.body() != null) {
                        sessionId = Integer.parseInt(sessionResponseResponse.body().getSessionResponseModel().getStartedSessionId());
                        initializeClassViewPagerAdapter();
                        initializeTabLayout();
                    }
                } else if (sessionResponseResponse.code() == ConfigurationFile.Constants.UNAUTHANTICATED_CODE) {
                    SharedUtils.getInstance().cancelDialog();
                    logout();
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

    private void initializeClassViewPagerAdapter() {
        classViewPagerAdapter = new ClassViewPagerAdapter(getSupportFragmentManager(), true);
        setFragmentBundleDataData(studentAttendanceFragment, ConfigurationFile.Constants.STUDENTS);
        setFragmentBundleDataData(announcementsFragment, ConfigurationFile.Constants.ANNONCEMENTS);
        setFragmentBundleDataData(studentQuizFragment, ConfigurationFile.Constants.QUIZZES);
        binding.viewpager.setAdapter(classViewPagerAdapter);
        binding.viewpager.setOffscreenPageLimit(1);
    }

    private void setFragmentBundleDataData(Fragment fragment, String title) {
        if (sessionType.equals(ConfigurationFile.Constants.NEWSESSION)) {
            Bundle bundle = new Bundle();
            bundle.putString(ConfigurationFile.Constants.SESSIONTYPE, ConfigurationFile.Constants.HISTORYSESSION);
            bundle.putInt(ConfigurationFile.Constants.SESSIONID, sessionId);
            fragment.setArguments(bundle);
            classViewPagerAdapter.addFragment(fragment, title);
        }else if (sessionType.equals(ConfigurationFile.Constants.HISTORYSESSION)){
            Bundle bundle = new Bundle();
            bundle.putString(ConfigurationFile.Constants.SESSIONTYPE, ConfigurationFile.Constants.HISTORYSESSION);
            bundle.putInt(ConfigurationFile.Constants.SESSIONID, historySessionId);
            fragment.setArguments(bundle);
            classViewPagerAdapter.addFragment(fragment, title);
        }
    }

    private void initializeVariables() {
        View headerView = ((LayoutInflater) Objects.requireNonNull(getSystemService(Context.LAYOUT_INFLATER_SERVICE)))
                .inflate(R.layout.tab1_layout, null, false);

        View headerView2 = ((LayoutInflater) Objects.requireNonNull(getSystemService(Context.LAYOUT_INFLATER_SERVICE)))
                .inflate(R.layout.tab2_layout, null, false);

        View headerView3 = ((LayoutInflater) Objects.requireNonNull(getSystemService(Context.LAYOUT_INFLATER_SERVICE)))
                .inflate(R.layout.tab3_layout, null, false);
        linearLayoutOne = (LinearLayout) headerView.findViewById(R.id.ll1);
        tvTab1 = (TextView) headerView.findViewById(R.id.tvTab1);
        linearLayout2 = (LinearLayout) headerView2.findViewById(R.id.ll1);
        tvTab2 = (TextView) headerView2.findViewById(R.id.tvTab2);
        linearLayout3 = (LinearLayout) headerView3.findViewById(R.id.ll1);
        tvTab3 = (TextView) headerView3.findViewById(R.id.tvTab3);
    }

    private void initializeTabLayout() {
        tabLayout = binding.tlClass;
        tabLayout.setupWithViewPager(binding.viewpager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setCustomView(linearLayoutOne);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setCustomView(linearLayout2);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setCustomView(linearLayout3);
        setTabSelectedListener();
    }

    private void setTabSelectedListener() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                markSelectedTab(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void markSelectedTab(TabLayout.Tab tab) {
        if (prev == 0)
            tvTab1.setTextColor(getResources().getColor(R.color.colorLightBlue));

        else if (prev == 1)
            tvTab2.setTextColor(getResources().getColor(R.color.colorLightBlue));

        else if (prev == 2)
            tvTab3.setTextColor(getResources().getColor(R.color.colorLightBlue));


        if (tab.getPosition() == 0)
            tvTab1.setTextColor(getResources().getColor(R.color.colorWhite));

        else if (tab.getPosition() == 1)
            tvTab2.setTextColor(getResources().getColor(R.color.colorWhite));

        else if (tab.getPosition() == 2)
            tvTab3.setTextColor(getResources().getColor(R.color.colorWhite));

        prev = tab.getPosition();
    }

    private void makeActionOnAssignmentButton() {
        binding.assignmentButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TeacherAssignmentActivity.class);
            intent.putExtra(ConfigurationFile.Constants.SESSIONID, historySessionId);
            intent.putExtra(ConfigurationFile.Constants.SESSIONTIMEMODEL, sectionTimeModel);
            startActivity(intent);

        });
    }
}
