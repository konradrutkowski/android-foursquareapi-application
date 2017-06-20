package konradrutkowski.com.tapapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import konradrutkowski.com.tapapp.customviews.CustomScrollView;
import konradrutkowski.com.tapapp.places.model.Place;
import konradrutkowski.com.tapapp.R;

public class DetailsActivity extends AppCompatActivity {

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
        Place place = (Place) bundle.getSerializable("fsquareObj");
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setElevation(0);
            newColor = new ColorDrawable(getResources().getColor(R.color.ColorFront));
            newColor.setAlpha(25);
            actionBar.setBackgroundDrawable(newColor);
            actionBar.setDisplayShowTitleEnabled(titleDisplay);
            if (place != null) {
                actionBar.setTitle(place.getName());
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
        initView(place);

        ImageView imageView = (ImageView) findViewById(R.id.imageBig);
        if (place != null) {
            Picasso.with(this).load(place.getUrl()).into(imageView);
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

    private void initView(Place mPlace) {
        TextView name = (TextView) findViewById(R.id.placeNameText);
        TextView category = (TextView) findViewById(R.id.categoryText);
        TextView distance = (TextView) findViewById(R.id.distanceText);
        TextView city = (TextView) findViewById(R.id.cityText);
        TextView checkins = (TextView) findViewById(R.id.chekinsText);
        TextView address = (TextView) findViewById(R.id.addressText);
        //

        if (mPlace.getName() != null) {
            name.setText(mPlace.getName());
        } else {
            name.setText("Not available");
        }

        if (mPlace.getCategory() != null) {
            category.setText(mPlace.getCategory());
        } else {
            category.setText("Not available");
        }
        if (mPlace.getDistance() != null) {
            distance.setText(mPlace.getDistance() + " meters");
        } else {
            distance.setText("Not available");
        }
        if (mPlace.getCheckins() != null) {
            checkins.setText(mPlace.getCheckins());
        } else {
            checkins.setText("Not available");
        }
        if (mPlace.getCity() != null) {
            city.setText(mPlace.getCity());
        } else {
            city.setText("Not available");
        }
        if (mPlace.getAddress() != null) {
            address.setText(mPlace.getAddress());
        } else {
            address.setText("Not available");
        }
    }
}
