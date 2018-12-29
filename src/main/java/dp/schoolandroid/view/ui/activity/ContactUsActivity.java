package dp.schoolandroid.view.ui.activity;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import dp.schoolandroid.R;
import dp.schoolandroid.Utility.utils.ConfigurationFile;
import dp.schoolandroid.Utility.utils.SetupAnimation;
import dp.schoolandroid.databinding.FragmentContactUsBinding;
import dp.schoolandroid.service.model.global.ContactInfoResponseModel;
import dp.schoolandroid.viewmodel.ContactUsActivityViewModel;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {


    ContactUsActivityViewModel viewModel;
    FragmentContactUsBinding binding;
    SupportMapFragment map;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_contact_us);
        SetupAnimation.getInstance().setUpAnimation(getWindow(), getResources());
        setupViewModel();
        setupGooglePlayMap();
    }

    private void setupViewModel() {
        viewModel=ViewModelProviders.of(this).get(ContactUsActivityViewModel.class);
        binding.setViewModel(viewModel);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getData().observe(this, contactInfoResponseModelResponse -> {
            if (contactInfoResponseModelResponse != null) {
                if (contactInfoResponseModelResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                    if (contactInfoResponseModelResponse.body() != null) {
                        initializeUiWithData(contactInfoResponseModelResponse.body().getContactInfo());
                    }
                } else{
                    Snackbar.make(binding.getRoot(), R.string.error, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeUiWithData(ContactInfoResponseModel contactInfo) {

    }

    private void setupGooglePlayMap() {
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mv_contact_us_map);
        map.getMapAsync(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(binding.getRoot(), connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(47.17, 27.5699), 16));
        googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                .anchor(0.0f, 1.0f)
                .title("Saudi Arabia")
                .position(new LatLng(47.17, 27.5699)));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }
}


