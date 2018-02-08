package konradrutkowski.com.tapapp.placedetails

import android.content.pm.ActivityInfo
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import konradrutkowski.com.tapapp.R
import konradrutkowski.com.tapapp.customviews.CustomScrollView
import konradrutkowski.com.tapapp.places.Place
import konradrutkowski.com.tapapp.places.PlacesFragment

class PlaceDetailsActivity : AppCompatActivity() {

    internal var actionBar: ActionBar? = null
    internal var newColor: ColorDrawable? = null
    internal var titleDisplay = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.details_fragment)
        val intent = this.intent
        val bundle = intent.extras
        val mFSquarePlace = bundle!!.getSerializable(PlacesFragment.FOURSQUARE_OBJECT_KEY) as Place
        actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar!!.setDisplayHomeAsUpEnabled(true)
//            actionBar!!.setDisplayShowHomeEnabled(true)
//            actionBar!!.elevation = 0f
//            newColor = ColorDrawable(resources.getColor(R.color.ColorFront))
//            newColor!!.alpha = 25
//            actionBar!!.setBackgroundDrawable(newColor)
//            actionBar!!.setDisplayShowTitleEnabled(titleDisplay)
//
//
//            actionBar!!.title = mFSquarePlace.name
//        }


        val scrollView = findViewById<View>(R.id.detailsScrollView) as CustomScrollView
        scrollView.setOnScrollViewListener(object : CustomScrollView.OnScrollViewListener {

            override fun onScrollChanged(v: CustomScrollView, l: Int, t: Int, oldl: Int, oldt: Int) {
                newColor!!.alpha = getAlphaforActionBar(v.scrollY)
                actionBar!!.setBackgroundDrawable(newColor)
                actionBar!!.setDisplayShowTitleEnabled(titleDisplay)
            }

            private fun getAlphaforActionBar(scrollY: Int): Int {
                val minDist = 10
                val maxDist = 450
                return when {
                    scrollY > maxDist -> {
                        titleDisplay = true
                        255
                    }
                    scrollY < minDist -> {
                        titleDisplay = false
                        10
                    }
                    else -> {
                        titleDisplay = false
                        val alpha: Int = (255.0 / maxDist * scrollY).toInt()
                        alpha
                    }
                }
            }
        })
        initView(mFSquarePlace)

        val imageView = findViewById<View>(R.id.imageBig) as ImageView
        if (mFSquarePlace.url != null) {
            //  imageLoader.displayImage(mFSquarePlace.getUrl(), imageView, options);
        } else {
            imageView.visibility = View.GONE
        }

        val addToFavButton = findViewById<View>(R.id.addToFav) as FloatingActionButton
        addToFavButton.setOnClickListener { Toast.makeText(application, "TODO Favorite section ", Toast.LENGTH_SHORT).show() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    private fun initView(mPlace: Place) {
        val name = findViewById<View>(R.id.placeNameText) as TextView
        val category = findViewById<View>(R.id.categoryText) as TextView
        val distance = findViewById<View>(R.id.distanceText) as TextView
        val city = findViewById<View>(R.id.cityText) as TextView
        val checkins = findViewById<View>(R.id.chekinsText) as TextView
        val address = findViewById<View>(R.id.addressText) as TextView
        //

        name.text = getOrDefault(mPlace.name)
        category.text = getOrDefault(mPlace.category)
        distance.text = buildDistance(mPlace)
        checkins.text = getOrDefault(mPlace.checkins)
        city.text = getOrDefault(mPlace.city)
        address.text = getOrDefault(mPlace.address)

    }

    private fun getOrDefault(inString: String?): String = inString
            ?: getString(R.string.not_available)

    private fun buildDistance(place: Place): String {
        if (!place.distance.isNullOrBlank()) {
            getString(R.string.meters, place.distance)
        }

        return getString(R.string.not_available)
    }

}
