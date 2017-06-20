package konradrutkowski.com.tapapp.places;

import java.util.List;

import konradrutkowski.com.tapapp.BaseView;
import konradrutkowski.com.tapapp.places.model.Place;

/**
 * Created by konrad on 20.06.2017.
 */

public interface PlacesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTasks(List<Place> places);

        void showPlaceDetails(String taskId);

    }

    interface Presenter{

       void loadData();
    }

}
