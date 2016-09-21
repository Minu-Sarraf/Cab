package com.cab.alington.cab.splashscreen;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cab.alington.cab.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


//oldone android:value="AIzaSyAFXN-1EgCpaHzk2Vv9XoQxRvBgN4jfX6k" />
public class MapsActivity extends FragmentActivity implements GoogleMap.OnMyLocationChangeListener, OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    EditText et;
    boolean result;
    ImageView search;
    String location;
    Marker userMarker;
    LatLng latLng;
    double lat;
    String mapadd;
    double lon;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabsave);
        fab.setOnClickListener(this);
        //  mMap.setOnMapClickListener(this);
        et = (EditText) findViewById(R.id.searchbar);

        search = (ImageView) findViewById(R.id.img_back);
        search.setOnClickListener(this);

        search = (ImageView) findViewById(R.id.img_back);
        sharedpreferences = AplicationActivity.getInstance().getSharedPreferences("map", Context.MODE_PRIVATE);
        et.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!checkinternet.isNetWorkAvailableNow(getBaseContext())) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertUtils.displaySnackBar(MapsActivity.this, "Poor Internet Connection!", R.color.primary);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertUtils.displaySnackBar(MapsActivity.this, "Either Search or just select your desired location", R.color.amber);
                        }
                    });

                }
            }
        });
        th.start();
        et.setOnEditorActionListener(new TextView.OnEditorActionListener()

        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                search123();
                return true;
            }
        });

    }

    public String getaddress() {
        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                Geocoder myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> myList = myLocation.getFromLocation(lat, lon, 1);
                    if (myList != null && myList.size() > 0) {

                        mapadd = myList.get(0).getAddressLine(0) + ", " + myList.get(0).getLocality();
                        Log.e("getadd", mapadd+"none");


                    }
                } catch (Exception e) {

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (userMarker != null) {
                            userMarker.setTitle(mapadd);
                            userMarker.showInfoWindow();
                        }


                    }
                });
            }
        });th.start();

        return mapadd;
    }

    private void search123() {
        if (userMarker != null) {
            userMarker.remove();
        }
        location = et.getText().toString();
        List<Address> address = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                address = geocoder.getFromLocationName(location, 1);
                if (address.size() > 0) {
                    Address address1 = address.get(0);
                    lat = address1.getLatitude();
                    lon = address1.getLongitude();
                    latLng = new LatLng(lat, lon);


                    addUserMarker(latLng);
                    //   mMap.addMarker(new MarkerOptions().position(latLng).title(mapadd));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
                }
                Log.e("searchlat", String.valueOf(latLng));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("serach", "serach");
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        result=Permission.Utility.checkPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION, 124,"Location permission is necessary");
        mMap.setOnMapClickListener(MapsActivity.this);
        Log.d("mymap1", "inside initializemap()");
        initializeMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 124: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setOnMapClickListener(this);
                    initializeMap();
                } else {
                    Toast.makeText(this, "No permission for contacts", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void initializeMap() {
        Log.d("mymap", "inside initializemap()");
        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_NORMAL)
                .compassEnabled(true)
                .tiltGesturesEnabled(true)
                .rotateGesturesEnabled(true)
                .zoomControlsEnabled(true)
                .zoomGesturesEnabled(true);
        mMap.setPadding(0, dpToPx(70), 0, 0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.3974557, 84.1299978), 5.5f));
        addUserMarker(new LatLng(28.3974557, 84.1299978));
        userMarker.setTitle("Nepal");
        userMarker.showInfoWindow();
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.fabsave):
                save_add();
            case (R.id.img_back):
                save_add();

                break;
        }
    }

    public void save_add() {
        if (latLng != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("lat", lat);
            returnIntent.putExtra("myadd", mapadd);
            returnIntent.putExtra("lon", lon);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

        } else {
            Intent returnIntent = new Intent();

            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
            // onBackPressed();
            //overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
        }
    }

    @Override
    public void onMapClick(LatLng point) {
        if (userMarker != null) {
            userMarker.remove();
        }
        lat = point.latitude;
        lon = point.longitude;
        double precision = Math.pow(10, 6);
        double new_Latitude = (((precision * lat)) / precision);
        double new_Longitude = (((precision * lat)) / precision);
        latLng = new LatLng(lat, lon);
        addUserMarker(latLng);

    }

    private void addUserMarker(final LatLng loc) {


          mapadd=getaddress();
        userMarker = mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("Loading....")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.home1)));
        userMarker.showInfoWindow();

    }

    @Override
    public void onMyLocationChange(Location location) {

        LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
        addUserMarker(loc);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 18f));
        mMap.setOnMyLocationChangeListener(null);

    }

}