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
import konradrutkowski.com.tapapp.online.ResponseRequestTask
import konradrutkowski.com.tapapp.util.Parser
import java.util.*


class DataCollector(var fragment: PlacesListFragment, private var swipeRefreshLayout: SwipeRefreshLayout, internal var latitude: String, internal var longitude: String) : AsyncTask<String, Void, PlacesAdapter>() {
    internal var temp: String? = null
    lateinit var places: ArrayList<Place>

    override fun onPreExecute() {
        swipeRefreshLayout.post { swipeRefreshLayout.isRefreshing = true }
        super.onPreExecute()
    }

    override fun doInBackground(vararg strings: String): PlacesAdapter {
        temp = ResponseRequestTask.makeCall(latitude, longitude)
        places = Parser.parseFoursquare(temp!!)
        val db = PlacesSQLiteHelper(fragment.activity.applicationContext)
        db.onUpgrade(db.writableDatabase, 1, 2)

        //TODO PERFORMANCE ISSUES
        for (fSquarePlace in places) {
            if (fSquarePlace.url == null) {
                val picResponse: String = fSquarePlace.id?.let { ResponseRequestTask.makeCall(it) }?: ""
                fSquarePlace.url = Parser.parsePictureURL(picResponse)
                db.createFSquarePlace(fSquarePlace)

            }
        }
        db.close()
        return PlacesAdapter(fragment.activity, places)
    }

    override fun onPostExecute(placesAdapter: PlacesAdapter) {
        val listView = fragment.activity.findViewById<View>(R.id.listView) as ListView
        listView.adapter = placesAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ -> fragment.showDetails(placesAdapter.getItem(i)) }

        swipeRefreshLayout.isRefreshing = false
        super.onPostExecute(placesAdapter)
    }
}


