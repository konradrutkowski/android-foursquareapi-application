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


public class OfflineDataCollector extends AsyncTask<String, Void, FSquareAdapter> {

    PlacesListFragment fragment;
    SwipeRefreshLayout swipeRefreshLayout;

    public OfflineDataCollector(PlacesListFragment fragment, SwipeRefreshLayout swipeRefreshLayout) {
        this.fragment = fragment;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected FSquareAdapter doInBackground(String... strings) {

        ArrayList<FSquarePlace> placeList;
        PlacesSQLiteHelper placesSQLiteHelper = new PlacesSQLiteHelper(fragment.getActivity().getApplicationContext());
        placeList = placesSQLiteHelper.getAllFSquarePlaces();
        return new FSquareAdapter(fragment.getActivity(), placeList);
    }

    @Override
    protected void onPostExecute(final FSquareAdapter fSquareAdapter) {

        final ListView listView = (ListView) fragment.getActivity().findViewById(R.id.listView);
        listView.setAdapter(fSquareAdapter);

        swipeRefreshLayout.setRefreshing(false);
        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fragment.showDetails(fSquareAdapter.getItem(i));
            }
        }
        ));
        super.onPostExecute(fSquareAdapter);
    }
}


