package vn.tdtu.mad.learn.Screens;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import vn.tdtu.mad.learn.R;



public class MapsActivity extends AppCompatActivity
        implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    private static final int PERMISSION_CODE = 2222;
    private String type;
    private String redeemable;
    Intent intent;
    private Button btnRedeem, btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intent = getIntent();
        type = intent.getStringExtra("Type");
        redeemable = intent.getStringExtra("Redeemable");
        btnRedeem = findViewById(R.id.btnRedeem);
        btnCancel = findViewById(R.id.btnCancel);

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("@@@@", "Maps isRedeemable " + redeemable );
                int isRedeemed = Integer.valueOf(redeemable);

                if(isRedeemed == 1){
                setResult(RESULT_OK);
                finish();
                }
                else if(isRedeemed == 0){

                    CharSequence charSequence = "Not enough Credits!";
                    Context context = getApplicationContext();
                    Toast.makeText(context, charSequence, Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_CODE);
            }
            map.setMyLocationEnabled(true);
            map.setOnMyLocationButtonClickListener(this);
            map.setOnMyLocationClickListener(this);

            LatLng burgerKing = new LatLng(10.77119051628578, 106.6682195470158);
            LatLng mcDonald = new LatLng(10.793746950410203, 106.69856122212512);
            LatLng fortnite = new LatLng(10.773041582870238, 106.6531133236319);


            if(type != null){
            switch(type){
                case "McDonalds":
                    map.addMarker(new MarkerOptions().position(mcDonald).title("Redeem here at McDonalds"));
                    break;
                case "BurgerKing":
                    map.addMarker(new MarkerOptions().position(burgerKing).title("Redeem here at BurgerKing"));
                    break;
                case "Fortnite":
                    map.addMarker(new MarkerOptions().position(fortnite).title("Redeem here at Fortnite Shop"));
                    break;
                default:
                    break;

            }
            }else{
                Log.e("GOOGLE", "Types null!");
            }
        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
                .show();
        return false;
    }



}