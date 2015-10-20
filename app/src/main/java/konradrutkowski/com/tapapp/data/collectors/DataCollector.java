package konradrutkowski.com.tapapp.data.collectors;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.fsquare.FSquareAdapter;
import konradrutkowski.com.tapapp.fsquare.FSquarePlace;
import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.data.PlacesSQLiteHelper;
import konradrutkowski.com.tapapp.fragments.PlacesListFragment;
import konradrutkowski.com.tapapp.online.ResponseRequestTask;
import konradrutkowski.com.tapapp.util.Parser;


public class DataCollector extends AsyncTask<String, Void, FSquareAdapter> {
    String temp = null;
    PlacesListFragment fragment;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<FSquarePlace> fSquarePlaces;
    String latitude;
    String longitude;

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
    protected FSquareAdapter doInBackground(String... strings) {
        temp = ResponseRequestTask.makeCall(latitude, longitude);
        fSquarePlaces = Parser.parseFoursquare(temp);
        PlacesSQLiteHelper db = new PlacesSQLiteHelper(fragment.getActivity().getApplicationContext());
        db.onUpgrade(db.getWritableDatabase(), 1, 2);

        //TODO PERFORMANCE ISSUES
        for (FSquarePlace fSquarePlace : fSquarePlaces) {
            if (fSquarePlace.getUrl() == null) {
                String picResponse;
                picResponse = ResponseRequestTask.makeCall(fSquarePlace.getID());
                fSquarePlace.setUrl(Parser.parsePictureURL(picResponse));
                db.createFSquarePlace(fSquarePlace);

            }
        }
        db.close();
        return new FSquareAdapter(fragment.getActivity(), fSquarePlaces);
    }

    @Override
    protected void onPostExecute(final FSquareAdapter fSquareAdapter) {
        final ListView listView = (ListView) fragment.getActivity().findViewById(R.id.listView);
        listView.setAdapter(fSquareAdapter);
        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment.showDetails(fSquareAdapter.getItem(i));
            }
        }
        ));

        swipeRefreshLayout.setRefreshing(false);
        super.onPostExecute(fSquareAdapter);
    }
}


