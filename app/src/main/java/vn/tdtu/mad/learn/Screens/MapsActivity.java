package vn.tdtu.mad.learn.Screens;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import vn.tdtu.mad.learn.R;
import vn.tdtu.mad.learn.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng burgerKing = new LatLng(37.39559867843787, -122.02936104232823);
        LatLng mcDonald = new LatLng(37.4195331257273, -122.09288621205896);

        LatLng myLocation = new LatLng(37.42267908312313, -122.08374637725161);
        mMap.addMarker(new MarkerOptions()
                .position(burgerKing).title("BurgerKing"));
        mMap.addMarker(new MarkerOptions()
                .position(mcDonald).title("mcDonald"));

        mMap.addPolyline(new PolylineOptions().add(
                burgerKing, myLocation
        )
                        .width(10)
                        .color(Color.RED)

        );
        mMap.addPolyline(new PolylineOptions().add(
                        mcDonald, myLocation
                )
                .width(10)
                .color(Color.RED));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
          mMap.setMyLocationEnabled(true);
    }
}