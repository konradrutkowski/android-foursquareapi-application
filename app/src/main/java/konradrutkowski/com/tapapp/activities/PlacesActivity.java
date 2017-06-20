package konradrutkowski.com.tapapp.activities;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.places.view.PlacesListFragment;

public class PlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Places");
        }
        initListPlacesFragment();
    }

    private void initListPlacesFragment(){
        PlacesListFragment placesListFragment = new PlacesListFragment();
        loadFragment(R.id.fragmentContainer, placesListFragment, "PlacesList");
    }

    private void loadFragment(int container, Fragment fragment, String name) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(container, fragment, name).commit();
    }
}


