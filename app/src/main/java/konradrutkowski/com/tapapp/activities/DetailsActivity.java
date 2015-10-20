package konradrutkowski.com.tapapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import konradrutkowski.com.tapapp.customviews.CustomScrollView;
import konradrutkowski.com.tapapp.fsquare.FSquarePlace;
import konradrutkowski.com.tapapp.R;

public class DetailsActivity extends ActionBarActivity {

    ActionBar actionBar;
    ColorDrawable newColor = null;
    boolean titleDisplay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.details_fragment);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        FSquarePlace mFSquarePlace = (FSquarePlace) bundle.getSerializable("fsquareObj");
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setElevation(0);
            newColor = new ColorDrawable(getResources().getColor(R.color.ColorFront));
            newColor.setAlpha(25);
            actionBar.setBackgroundDrawable(newColor);
            actionBar.setDisplayShowTitleEnabled(titleDisplay);
            if (mFSquarePlace != null) {
                actionBar.setTitle(mFSquarePlace.getName());
            }
        }


        CustomScrollView scrollView = (CustomScrollView) findViewById(R.id.detailsScrollView);
        scrollView.setOnScrollViewListener(new CustomScrollView.OnScrollViewListener() {

            @Override
            public void onScrollChanged(CustomScrollView v, int l, int t, int oldl, int oldt) {
                newColor.setAlpha(getAlphaforActionBar(v.getScrollY()));
                actionBar.setBackgroundDrawable(newColor);
                actionBar.setDisplayShowTitleEnabled(titleDisplay);
            }

            private int getAlphaforActionBar(int scrollY) {
                int minDist = 10, maxDist = 450;
                if (scrollY > maxDist) {
                    titleDisplay = true;
                    return 255;
                } else if (scrollY < minDist) {
                    titleDisplay = false;
                    return 10;
                } else {
                    titleDisplay = false;
                    int alpha = 0;
                    alpha = (int) ((255.0 / maxDist) * scrollY);
                    return alpha;
                }
            }
        });
        initView(mFSquarePlace);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false).cacheOnDisk(true).build();
        ImageView imageView = (ImageView) findViewById(R.id.imageBig);
        assert mFSquarePlace != null;
        if (mFSquarePlace.getUrl() != null) {
            imageLoader.displayImage(mFSquarePlace.getUrl(), imageView, options);
        } else {
            imageView.setVisibility(View.GONE);
        }

        final FloatingActionButton addToFavButton = (FloatingActionButton) findViewById(R.id.addToFav);
        addToFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplication(), "TODO Favorite section ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private void initView(FSquarePlace mFSquarePlace) {
        TextView name = (TextView) findViewById(R.id.placeNameText);
        TextView category = (TextView) findViewById(R.id.categoryText);
        TextView distance = (TextView) findViewById(R.id.distanceText);
        TextView city = (TextView) findViewById(R.id.cityText);
        TextView checkins = (TextView) findViewById(R.id.chekinsText);
        TextView address = (TextView) findViewById(R.id.addressText);
        //

        if (mFSquarePlace.getName() != null) {
            name.setText(mFSquarePlace.getName());
        } else {
            name.setText("Not available");
        }

        if (mFSquarePlace.getCategory() != null) {
            category.setText(mFSquarePlace.getCategory());
        } else {
            category.setText("Not available");
        }
        if (mFSquarePlace.getDistance() != null) {
            distance.setText(mFSquarePlace.getDistance() + " meters");
        } else {
            distance.setText("Not available");
        }
        if (mFSquarePlace.getCheckins() != null) {
            checkins.setText(mFSquarePlace.getCheckins());
        } else {
            checkins.setText("Not available");
        }
        if (mFSquarePlace.getCity() != null) {
            city.setText(mFSquarePlace.getCity());
        } else {
            city.setText("Not available");
        }
        if (mFSquarePlace.getAddress() != null) {
            address.setText(mFSquarePlace.getAddress());
        } else {
            address.setText("Not available");
        }
    }
}
