package konradrutkowski.com.tapapp.fsquare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.R;
import konradrutkowski.com.tapapp.places.model.Place;


public class PlacesAdapter extends ArrayAdapter<Place> {


    public PlacesAdapter(Context context, ArrayList<Place> listArray) {
        super(context, 0, listArray);
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        View vi = view;
        ViewHolder viewHolder = new ViewHolder();
        Place actualFSquare = getItem(index);

        if (vi == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            vi = inflater.inflate(R.layout.places_list, parent, false);
            viewHolder.title = (TextView) vi.findViewById(R.id.titleText);
            viewHolder.category = (TextView) vi.findViewById(R.id.categoryText);
            viewHolder.icon = (ImageView) vi.findViewById(R.id.placeImage);
            vi.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) vi.getTag();
        }

        if (actualFSquare != null) {
            viewHolder.title.setText(actualFSquare.getName());
            viewHolder.category.setText(actualFSquare.getCategory());
            Picasso.with(getContext()).load(actualFSquare.getUrl()).into(viewHolder.icon);
        }
        return vi;
    }

    static class ViewHolder {
        TextView title;
        TextView category;
        ImageView icon;
    }
}

