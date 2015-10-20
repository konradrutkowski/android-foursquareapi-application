package konradrutkowski.com.tapapp.fsquare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.R;


public class FSquareAdapter extends ArrayAdapter<FSquarePlace> {


    public FSquareAdapter(Context context, ArrayList<FSquarePlace> listArray) {
        super(context, 0, listArray);
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(false).showImageOnFail(R.mipmap.ic_launcher).cacheOnDisk(true).build();
        View vi = view;
        ViewHolder viewHolder = new ViewHolder();
        FSquarePlace actualFSquare = getItem(index);

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

        viewHolder.title.setText(actualFSquare.getName());
        viewHolder.category.setText(actualFSquare.getCategory());

        if (actualFSquare.getUrl() != null && viewHolder.icon != null) {
            imageLoader.displayImage(actualFSquare.getUrl(), viewHolder.icon, options);
        }
        return vi;
    }

    static class ViewHolder {
        TextView title;
        TextView category;
        ImageView icon;
    }
}

