package konradrutkowski.com.tapapp.activities

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
import konradrutkowski.com.tapapp.fsquare.Place

class DetailsActivity : AppCompatActivity() {

    internal var actionBar: ActionBar? = null
    internal var newColor: ColorDrawable? = null
    internal var titleDisplay = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.details_fragment)
        val intent = this.intent
        val bundle = intent.extras
        val mFSquarePlace = bundle!!.getSerializable("fsquareObj") as Place
        actionBar = supportActionBar
        if (actionBar != null) {
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar!!.setDisplayShowHomeEnabled(true)
            actionBar!!.elevation = 0f
            newColor = ColorDrawable(resources.getColor(R.color.ColorFront))
            newColor!!.alpha = 25
            actionBar!!.setBackgroundDrawable(newColor)
            actionBar!!.setDisplayShowTitleEnabled(titleDisplay)


            actionBar!!.title = mFSquarePlace.name
        }


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
                if (scrollY > maxDist) {
                    titleDisplay = true
                    return 255
                } else if (scrollY < minDist) {
                    titleDisplay = false
                    return 10
                } else {
                    titleDisplay = false
                    var alpha: Int
                    alpha = (255.0 / maxDist * scrollY).toInt()
                    return alpha
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
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
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

        if (mPlace.name != null) {
            name.text = mPlace.name
        } else {
            name.text = "Not available"
        }

        if (mPlace.category != null) {
            category.text = mPlace.category
        } else {
            category.text = "Not available"
        }
        if (mPlace.distance != null) {
            distance.text = mPlace.distance + " meters"
        } else {
            distance.text = "Not available"
        }
        if (mPlace.checkins != null) {
            checkins.text = mPlace.checkins
        } else {
            checkins.text = "Not available"
        }
        if (mPlace.city != null) {
            city.text = mPlace.city
        } else {
            city.text = "Not available"
        }
        if (mPlace.address != null) {
            address.text = mPlace.address
        } else {
            address.text = "Not available"
        }
    }
}
