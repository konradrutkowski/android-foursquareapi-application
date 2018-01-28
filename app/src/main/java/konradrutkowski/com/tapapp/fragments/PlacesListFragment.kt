package konradrutkowski.com.tapapp.fragments

import android.app.Fragment
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import konradrutkowski.com.tapapp.R
import konradrutkowski.com.tapapp.activities.DetailsActivity
import konradrutkowski.com.tapapp.data.collectors.DataCollector
import konradrutkowski.com.tapapp.data.collectors.OfflineDataCollector
import konradrutkowski.com.tapapp.fsquare.Place
import konradrutkowski.com.tapapp.online.NetworkConnection
import konradrutkowski.com.tapapp.util.LocationTracker
import kotlinx.android.synthetic.main.places_list_fragment.*

class PlacesListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val locationTracker: LocationTracker by lazy { LocationTracker(activity) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        return inflater.inflate(R.layout.places_list_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initSwipe()
    }

    private fun initSwipe() {
        swipe_layout.run {
            setOnRefreshListener(this@PlacesListFragment)
            setColorSchemeColors(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN)
            setProgressViewOffset(true, 100, 300)
        }
    }

    fun showDetails(place: Place) {
        val bundle = Bundle()
        bundle.putParcelable("fsquareObj", place)

        val detailsIntent = Intent(activity, DetailsActivity::class.java)
        detailsIntent.putExtras(bundle)

        startActivity(detailsIntent)
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    override fun onRefresh() {
        if (locationTracker.canGetLocation() && NetworkConnection.isInternetReady(this.activity)) {
            ///RequestConfig.latitude, RequestConfig.longtitude);// FAKELOC
            val dataCollector = DataCollector(this, swipe_layout, locationTracker.latitude.toString(), locationTracker.longitude.toString())
            dataCollector.execute()
        } else {
            val offlineDataCollector = OfflineDataCollector(this, swipe_layout)
            offlineDataCollector.execute()
        }

    }

    override fun onDestroy() {
        locationTracker.stopUsingGPS()
        super.onDestroy()
    }
}


