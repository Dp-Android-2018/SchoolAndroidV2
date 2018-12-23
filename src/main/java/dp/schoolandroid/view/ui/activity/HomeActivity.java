package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.ActivityHomeBinding;
import dp.schoolandroid.view.ui.fragment.BaseFragmentWithData;
import dp.schoolandroid.view.ui.fragment.ScheduleFragment;
import dp.schoolandroid.view.ui.fragment.TopStudentFragment;
import dp.schoolandroid.viewmodel.HomeActivityViewModel;

/*
 * this class is responsible for get and set up home Details
 */
public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private Intent intent;
    private Fragment selectedFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        HomeActivityViewModel viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel.class);
        binding.setViewModel(viewModel);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setNavigationDrawer();
        setBottonNavigationView();
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
            switch (item.getItemId()) {
                case R.id.menu_home:
                    break;
                case R.id.menu_edit_profile:
                    openProfileActivity();
                    break;
                case R.id.menu_message:
                    openChatActivity();
                    break;
                case R.id.menu_about_us:
                    openAboutUsActivity();
                    break;
                case R.id.menu_picture_gallery:
                    openPictureGalleryActivity();
                    break;
                case R.id.menu_suggestions:
                    openSuggestionsActivity();
                    break;
                case R.id.menu_contact_us:
                    openContactUsActivity();
                    break;
                case R.id.menu_logOut:
                    logout();
                    break;
            }
            return true;
        });
    }

    private void logout() {
    }

    private void openContactUsActivity() {
    }

    private void openSuggestionsActivity() {
    }

    private void openPictureGalleryActivity() {
    }

    private void openAboutUsActivity() {

    }

    private void openChatActivity() {
        intent = new Intent(HomeActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    private void openProfileActivity() {
        intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
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

    //this function is to setup bottom navigation view
    public void setBottonNavigationView() {
        final BottomNavigationView bottomNavigationView = binding.navigation;
        bottomNavigationView.setOnNavigationItemSelectedListener
                (
                        item -> {
                            switch (item.getItemId()) {
                                case R.id.action_item1:
                                    item.setIcon(R.drawable.ic_home_on);
                                    selectedFragment = BaseFragmentWithData.newInstance();
                                    break;
                                case R.id.action_item2:
                                    item.setIcon(R.drawable.ic_calender_on);
                                    selectedFragment = new ScheduleFragment();
                                    break;
                                case R.id.action_item3:
                                    item.setIcon(R.drawable.ic_student_on);
                                    selectedFragment = TopStudentFragment.newInstance();
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
        transaction.replace(R.id.frame_layout, BaseFragmentWithData.newInstance());
        transaction.commit();
    }
}
