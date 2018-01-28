package konradrutkowski.com.tapapp.fsquare

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import konradrutkowski.com.tapapp.R
import java.util.*


class PlacesAdapter(context: Context, listArray: ArrayList<Place>) : ArrayAdapter<Place>(context, 0, listArray) {

    override fun getView(index: Int, view: View?, parent: ViewGroup): View {

        var vi = view
        var viewHolder = ViewHolder()
        val actualFSquare = getItem(index)

        if (vi == null) {
            val inflater = LayoutInflater.from(parent.context)
            vi = inflater.inflate(R.layout.places_list, parent, false)
            viewHolder.title = vi!!.findViewById<View>(R.id.titleText) as TextView
            viewHolder.category = vi.findViewById<View>(R.id.categoryText) as TextView
            viewHolder.icon = vi.findViewById<View>(R.id.placeImage) as ImageView
            vi.tag = viewHolder
        } else {
            viewHolder = vi.tag as ViewHolder
        }

        viewHolder.title!!.text = actualFSquare!!.name
        viewHolder.category!!.text = actualFSquare.category

        if (actualFSquare.url != null && viewHolder.icon != null) {
            //   imageLoader.displayImage(actualFSquare.getUrl(), viewHolder.icon, options);
        }
        return vi
    }

    internal class ViewHolder {
        var title: TextView? = null
        var category: TextView? = null
        var icon: ImageView? = null
    }
}

