package konradrutkowski.com.tapapp.data.collectors;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.fsquare.PlacesAdapter;
import konradrutkowski.com.tapapp.places.model.Place;
import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.data.PlacesSQLiteHelper;
import konradrutkowski.com.tapapp.places.view.PlacesListFragment;
import konradrutkowski.com.tapapp.online.ResponseRequestTask;
import konradrutkowski.com.tapapp.util.Parser;


public class DataCollector extends AsyncTask<String, Void, PlacesAdapter> {
    private String temp = null;
    private PlacesListFragment fragment;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Place> places;
    private String latitude;
    private String longitude;

    public DataCollector(PlacesListFragment fragment, SwipeRefreshLayout swipeRefreshLayout, String latitude, String longitude) {
        this.fragment = fragment;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected void onPreExecute() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        super.onPreExecute();
    }

    @Override
    protected PlacesAdapter doInBackground(String... strings) {
        temp = ResponseRequestTask.makeCall(latitude, longitude);
        places = Parser.parseFoursquare(temp);
        PlacesSQLiteHelper db = new PlacesSQLiteHelper(fragment.getActivity().getApplicationContext());
        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        //TODO PERFORMANCE ISSUES
        for (Place place : places) {
            if (place.getUrl() == null) {
                String picResponse;
                picResponse = ResponseRequestTask.makeCall(place.getID());
                place.setUrl(Parser.parsePictureURL(picResponse));
                db.createFSquarePlace(place);
            }
        }
        db.close();
        return new PlacesAdapter(fragment.getActivity(), places);
    }

    @Override
    protected void onPostExecute(final PlacesAdapter placesAdapter) {
        final ListView listView = (ListView) fragment.getActivity().findViewById(R.id.listView);
        listView.setAdapter(placesAdapter);
        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment.showDetails(placesAdapter.getItem(i));
            }
        }
        ));

        swipeRefreshLayout.setRefreshing(false);
        super.onPostExecute(placesAdapter);
    }
}


