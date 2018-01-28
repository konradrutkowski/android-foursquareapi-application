package konradrutkowski.com.tapapp.data.collectors

import android.os.AsyncTask
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import konradrutkowski.com.tapapp.R
import konradrutkowski.com.tapapp.data.PlacesSQLiteHelper
import konradrutkowski.com.tapapp.fragments.PlacesListFragment
import konradrutkowski.com.tapapp.fsquare.Place
import konradrutkowski.com.tapapp.fsquare.PlacesAdapter
import java.util.*


class OfflineDataCollector(internal var fragment: PlacesListFragment, internal var swipeRefreshLayout: SwipeRefreshLayout) : AsyncTask<String, Void, PlacesAdapter>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg strings: String): PlacesAdapter {

        val placeList: ArrayList<Place>
        val placesSQLiteHelper = PlacesSQLiteHelper(fragment.activity.applicationContext)
        placeList = placesSQLiteHelper.allPlaces
        return PlacesAdapter(fragment.activity, placeList)
    }

    override fun onPostExecute(placesAdapter: PlacesAdapter) {

        val listView = fragment.activity.findViewById<View>(R.id.listView) as ListView
        listView.adapter = placesAdapter

        swipeRefreshLayout.isRefreshing = false
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l -> fragment.showDetails(placesAdapter.getItem(i)) }
        super.onPostExecute(placesAdapter)
    }
}


