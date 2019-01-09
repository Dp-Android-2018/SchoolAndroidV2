package dp.schoolandroid.view.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.CustomUtils;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityHomeBinding;
import dp.schoolandroid.di.component.DaggerFragmentComponent;
import dp.schoolandroid.di.component.FragmentComponent;
import dp.schoolandroid.view.ui.fragment.BaseFragmentWithData;
import dp.schoolandroid.view.ui.fragment.NewsFeedFragment;
import dp.schoolandroid.view.ui.fragment.ScheduleFragment;
import dp.schoolandroid.view.ui.fragment.TopStudentFragment;

/*
 * this class is responsible for get and set up home Details
 * make actions when clicking on navigation drawer
 * make actions when clicking on Bottom navigation view
 */

public class StudentHomeActivity extends AppCompatActivity {

    @Inject
    ScheduleFragment scheduleFragment;
    @Inject
    TopStudentFragment topStudentFragment;
    @Inject
    NewsFeedFragment newsFeedFragment;

    ActivityHomeBinding binding;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Fragment selectedFragment;
    private CustomUtils customUtils;
    private String memberType;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupDaggerFragmentComponent();
        initializeViewModel();
        setNavigationDrawer();
        setBottonNavigationView();
    }

    private void initializeViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        customUtils = new CustomUtils(getApplication());
    }

    private void setupDaggerFragmentComponent() {
        FragmentComponent component = DaggerFragmentComponent.create();
        component.inject(this);
    }

    public void setNavigationDrawer() {
        initializeDrawerandNavigationView();
        setNavigationItemSelectedListener();
    }

    private void initializeDrawerandNavigationView() {
        drawer = binding.mainActivity;
        ConstraintLayout content = binding.content;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.Open, R.string.Close) {
            private float scaleFactor = 6f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);
                content.setScaleX(1 - (slideOffset / scaleFactor));
                content.setScaleY(1 - (slideOffset / scaleFactor));
            }
        };
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.setDrawerElevation(0f);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void setNavigationItemSelectedListener() {
        setupHeaderData();
        binding.nv.setNavigationItemSelectedListener(item -> {
            closeDrawer();
            makeActionOnNavigationItem(item.getItemId());
            return true;
        });
    }

    private void setupHeaderData() {
        View header = binding.nv.getHeaderView(0);
        ImageView teacherPhoto= (ImageView) header.findViewById(R.id.student_photo);
        Picasso.get().load(customUtils.getSavedStudentData().getStudentResponseData().getImage()).into(teacherPhoto);
        TextView teacherName = (TextView) header.findViewById(R.id.tv_teacher_name);
        teacherName.setText(customUtils.getSavedStudentData().getStudentResponseData().getName());
        TextView teacherMail = (TextView) header.findViewById(R.id.tv_teacher_email);
        teacherMail.setText(customUtils.getSavedStudentData().getStudentResponseData().getEmail());
    }

    private void makeActionOnNavigationItem(int itemId) {
        switch (itemId) {
            case R.id.menu_home:
                openIntent(StudentHomeActivity.class);
                break;
            case R.id.menu_edit_profile:
                openIntent(ProfileActivity.class);
                break;
            case R.id.menu_message:
                openIntent(ChatActivity.class);
                break;
            case R.id.menu_about_us:
                openIntent(AboutUsActivity.class);
                break;
            case R.id.menu_picture_gallery:
                openIntent(PictureGalleryActivity.class);
                break;
            case R.id.menu_videos:
                openIntent(VideosActivity.class);
                break;
            case R.id.menu_suggestions:
                openIntent(SuggestionActivity.class);
                break;
            case R.id.menu_contact_us:
                openIntent(ContactUsActivity.class);
                break;
            case R.id.menu_logOut:
                logout();
                break;
        }
    }

    private void openIntent(Class activityClass) {
        Intent intent = new Intent(StudentHomeActivity.this, activityClass);
        intent.putExtra(ConfigurationFile.Constants.MEMBER_Key,ConfigurationFile.Constants.STUDENT_Key_VALUE);
        startActivity(intent);
    }

    private void logout() {
        clearSharedPreferences();
        openIntent(MainActivity.class);
        finish();
    }

    private void clearSharedPreferences() {
        customUtils.clearSharedPref();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public void setBottonNavigationView() {
        binding.navigation.setOnNavigationItemSelectedListener
                (
                        item -> {
                            setSelectedFragment(item.getItemId());
                            openSelectedFragment();
                            return true;
                        });
        manuallyDisplayFirstFragment();
    }

    private void setSelectedFragment(int itemId) {
        switch (itemId) {
            case R.id.action_item1:
                setFragmentBundleData(newsFeedFragment);
                break;
            case R.id.action_item2:
                setFragmentBundleData(scheduleFragment);
                break;
            case R.id.action_item3:
                setFragmentBundleData(topStudentFragment);
                break;
        }

    }

    private void setFragmentBundleData(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString(ConfigurationFile.Constants.MEMBER_Key, ConfigurationFile.Constants.STUDENT_Key_VALUE);
        fragment.setArguments(bundle);
        selectedFragment = fragment;
    }

    private void openSelectedFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

    private void manuallyDisplayFirstFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(ConfigurationFile.Constants.MEMBER_Key, ConfigurationFile.Constants.STUDENT_Key_VALUE);
        newsFeedFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, newsFeedFragment);
        transaction.commit();
    }
}
