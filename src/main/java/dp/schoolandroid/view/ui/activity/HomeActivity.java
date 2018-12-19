package dp.schoolandroid.view.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.view.ui.fragment.BaseFragmentWithData;
import dp.schoolandroid.databinding.ActivityHomeBinding;
import dp.schoolandroid.view.ui.fragment.ContactUsFragment;
import dp.schoolandroid.view.ui.fragment.FeedsFragment;
import dp.schoolandroid.R;
import dp.schoolandroid.view.ui.fragment.ScheduleFragment;
import dp.schoolandroid.view.ui.fragment.SuggestionFragment;
import dp.schoolandroid.view.ui.fragment.TopStudentFragment;
import dp.schoolandroid.viewmodel.HomeActivityViewModel;

/*
 * this class is responsible for get and set up home Details
 */
public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle t;

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
        drawer = binding.mainActivity;
        t = new ActionBarDrawerToggle(this, drawer, R.string.Open, R.string.Close);
        drawer.addDrawerListener(t);
        t.syncState();
        NavigationView nv = binding.nv;
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        drawer.closeDrawer(GravityCompat.START);
                        Snackbar.make(binding.getRoot(), "Select Country First", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.menu_edit_profile:
                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent);
//                        Toast.makeText(HomeActivity.this, "menu_edit_profile", Toast.LENGTH_SHORT).show();
                        Snackbar.make(binding.getRoot(), "menu_edit_profile", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.menu_message:
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "menu_message", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about_us:
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "menu_about_us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_picture_gallery:
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "menu_picture_gallery", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_suggestions:
                        selectedFragment = SuggestionFragment.newInstance();
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "menu_suggestions", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_contact_us:
                        selectedFragment = ContactUsFragment.newInstance();
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "menu_contact_us", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_logOut:
                        drawer.closeDrawer(GravityCompat.START);
                        Toast.makeText(HomeActivity.this, "menu_logOut", Toast.LENGTH_SHORT).show();
                        break;
                }
                if (selectedFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //this function is to setup bottom navigation view
    public void setBottonNavigationView() {
        final BottomNavigationView bottomNavigationView = binding.navigation;
        bottomNavigationView.setOnNavigationItemSelectedListener
                (
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                Fragment selectedFragment = null;
                                switch (item.getItemId()) {
                                    case R.id.action_item1:
                                        item.setIcon(R.drawable.ic_home_on);
                                        selectedFragment = BaseFragmentWithData.newInstance();
                                        break;
                                    case R.id.action_item2:
                                        item.setIcon(R.drawable.ic_calender_on);
                                        //selectedFragment = ScheduleFragment.newInstance();
                                        selectedFragment = new ScheduleFragment();
                                        break;
                                    case R.id.action_item3:
                                        item.setIcon(R.drawable.ic_student_on);
                                        selectedFragment = TopStudentFragment.newInstance();
                                        break;
                                }
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frame_layout, selectedFragment);
                                transaction.commit();
                                return true;
                            }
                        });
        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, BaseFragmentWithData.newInstance());
        transaction.commit();
    }
}
