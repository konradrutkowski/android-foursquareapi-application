package konradrutkowski.com.tapapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import konradrutkowski.com.tapapp.R
import konradrutkowski.com.tapapp.fragments.PlacesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_for_fragment)

        savedInstanceState ?: initListPlacesFragment()
    }

    private fun initListPlacesFragment() {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, PlacesListFragment(), "LIST").commit()
    }
}


