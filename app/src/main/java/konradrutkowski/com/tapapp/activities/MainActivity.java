package konradrutkowski.com.tapapp.activities;

import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.fragments.PlacesListFragment;

public class MainActivity extends AppCompatActivity {

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


    private void initListPlacesFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        PlacesListFragment placesListFragment = new PlacesListFragment();
        ft.add(R.id.fragmentContainer, placesListFragment, "LIST").commit();
    }
}


