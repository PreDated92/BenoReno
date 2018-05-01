package com.example.chesterchin.benoreno;

import android.app.Fragment;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class FragmentNavigation extends Fragment {

    View mRootView;
    MapView mMapView;
    TextInputEditText mMapSearchBox;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_navigation, container, false);

        mMapView = mRootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng penang = new LatLng(5.4163, 100.3328);
                googleMap.addMarker(new MarkerOptions().position(penang).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(penang).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        mMapSearchBox = mRootView.findViewById(R.id.searchMap);
        mMapSearchBox.setOnEditorActionListener(MapSearchAction());

        return mRootView;
    }

    private TextView.OnEditorActionListener MapSearchAction() {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_GO ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mMapSearchBox.getWindowToken(), 0);

                    SearchClicked sc = new SearchClicked(getActivity(), mMapSearchBox.getText().toString());
                    sc.execute();
                    mMapSearchBox.setText("", TextView.BufferType.EDITABLE);


                    return true;
                }
                return false;
            }
        };
    }

    private class SearchClicked extends AsyncTask<Void, Void, LatLng> {
        private String toSearch;
        private Context context;
        public LatLng Location;

        SearchClicked(Context context, String toSearch) {
            this.toSearch = toSearch;
            this.context = context;
        }

        @Override
        protected LatLng doInBackground(Void... voids) {

            Location = new LatLng(0,0);
            try {

                Geocoder geocoder = new Geocoder(context, Locale.UK);
                List<Address> results = geocoder.getFromLocationName(toSearch, 1);

                if (results.size() == 0) {
                    return Location;
                }

                Address address = results.get(0);

                // Now do something with this GeoPoint:
                Location = new LatLng(address.getLatitude(), address.getLongitude());

            } catch (Exception e) {
                Log.e("", "Something went wrong: ", e);
                return Location;
            }
            return Location;
        }

        @Override
        protected void onPostExecute(LatLng latLng) {
            super.onPostExecute(latLng);

            Toast.makeText(getActivity(), Location.toString(), Toast.LENGTH_SHORT).show();
            AddMarker(Location);
            MoveMap(Location);
        }
    }

    private void AddMarker(LatLng location)
    {
        // For dropping a marker at a point on the Map
        googleMap.addMarker(new MarkerOptions().position(location).title("Marker Title").snippet("Marker Description"));
    }

    private void MoveMap(LatLng location)
    {
        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
