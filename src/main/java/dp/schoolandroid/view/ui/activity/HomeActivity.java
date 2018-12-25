package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import javax.inject.Inject;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityHomeBinding;
import dp.schoolandroid.di.component.DaggerFragmentComponent;
import dp.schoolandroid.di.component.FragmentComponent;
import dp.schoolandroid.view.ui.fragment.BaseFragmentWithData;
import dp.schoolandroid.view.ui.fragment.ScheduleFragment;
import dp.schoolandroid.view.ui.fragment.TopStudentFragment;
import dp.schoolandroid.viewmodel.HomeActivityViewModel;

/*
 * this class is responsible for get and set up home Details
 * make actions when clicking on navigation drawer
 * make actions when clicking on Bottom navigation view
 */
public class HomeActivity extends AppCompatActivity {

    @Inject BaseFragmentWithData baseFragmentWithData;
    @Inject ScheduleFragment scheduleFragment;
    @Inject TopStudentFragment topStudentFragment;

    ActivityHomeBinding binding;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Fragment selectedFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupFragmentComponent();
        initializeViewModel();
        setNavigationDrawer();
        setBottonNavigationView();
    }

    private void initializeViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        HomeActivityViewModel viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel.class);
        binding.setViewModel(viewModel);
    }

    private void setupFragmentComponent() {
        FragmentComponent component=DaggerFragmentComponent.create();
        component.inject(this);
    }

    //this function is to setup navigetion drawer
    public void setNavigationDrawer() {
        initializeDrawerandNavigationView();
        setNavigationItemSelectedListener();
    }

    private void initializeDrawerandNavigationView() {
        drawer = binding.mainActivity;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.Open, R.string.Close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = binding.nv;
    }

    private void setNavigationItemSelectedListener() {
        navigationView.setNavigationItemSelectedListener(item -> {
            closeDrawer();
            makeActionOnNavigationItem(item.getItemId());
            return true;
        });
    }

    private void makeActionOnNavigationItem(int itemId) {
        switch (itemId) {
            case R.id.menu_home:
                openIntent(HomeActivity.class);
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
        Intent intent = new Intent(HomeActivity.this, activityClass);
        startActivity(intent);
    }

    private void logout() {
        openIntent(MainActivity.class);
        finish();
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
                            switch (item.getItemId()) {
                                case R.id.action_item1:
                                    selectedFragment = baseFragmentWithData;
                                    break;
                                case R.id.action_item2:
                                    selectedFragment = scheduleFragment;
                                    break;
                                case R.id.action_item3:
                                    selectedFragment = topStudentFragment;
                                    break;
                            }
                            openSelectedFragment();
                            return true;
                        });
        manuallyDisplayFirstFragment();
    }

    private void openSelectedFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

    private void manuallyDisplayFirstFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, baseFragmentWithData);
        transaction.commit();
    }
}
