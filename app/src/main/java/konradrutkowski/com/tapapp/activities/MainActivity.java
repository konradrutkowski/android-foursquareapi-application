package konradrutkowski.com.tapapp.activities;

import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.fragments.PlacesListFragment;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Places");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .diskCacheFileNameGenerator(new FileNameGenerator() {
                    @Override
                    public String generate(String name) {
                        name = name.replaceAll(".*/", "");
                        return name;
                    }
                })
                .denyCacheImageMultipleSizesInMemory()
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();

        ImageLoader.getInstance().init(config);
        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
            initListPlacesFragment();
        }
    }

    private void initListPlacesFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        PlacesListFragment placesListFragment = new PlacesListFragment();
        ft.add(R.id.fragmentContainer, placesListFragment, "LIST").commit();
    }
}


