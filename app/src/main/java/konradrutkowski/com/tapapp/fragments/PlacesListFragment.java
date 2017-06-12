package konradrutkowski.com.tapapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.activities.DetailsActivity;
import konradrutkowski.com.tapapp.data.collectors.DataCollector;
import konradrutkowski.com.tapapp.data.collectors.OfflineDataCollector;
import konradrutkowski.com.tapapp.fsquare.Place;
import konradrutkowski.com.tapapp.online.NetworkConnection;
import konradrutkowski.com.tapapp.online.RequestConfig;
import konradrutkowski.com.tapapp.util.LocationTracker;


public class PlacesListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeLayout;
    View rootView;
    Activity activity;
    LocationTracker locationTracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.places_list_fragment, container, false);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN);
        swipeLayout.setProgressViewOffset(true, 100, 300);
        activity = getActivity();
        locationTracker = new LocationTracker(activity);
        onRefresh();


        return rootView;
    }

    public void showDetails(Place place){
        Intent detailsIntent = new Intent(getActivity(), DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("fsquareObj", place);
        detailsIntent.putExtras(bundle);
            startActivity(detailsIntent);
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onRefresh() {
        if(locationTracker.canGetLocation() && NetworkConnection.isConnectingToInternet(this.getActivity())) {
          // RequestConfig.latitude, RequestConfig.longtitude;// FAKELOC
            DataCollector dataCollector = new DataCollector(this, swipeLayout,
                    String.valueOf(RequestConfig.latitude), String.valueOf(RequestConfig.longtitude));

            //  DataCollector dataCollector = new DataCollector(this, swipeLayout, String.valueOf(
          //          locationTracker.getLatitude()), String.valueOf(locationTracker.getLongitude()));
            dataCollector.execute();
        }else{
            OfflineDataCollector offlineDataCollector = new OfflineDataCollector(this, swipeLayout);
            offlineDataCollector.execute();
        }

    }

    @Override
    public void onDestroy() {
        locationTracker.stopUsingGPS();
        super.onDestroy();
    }
}


