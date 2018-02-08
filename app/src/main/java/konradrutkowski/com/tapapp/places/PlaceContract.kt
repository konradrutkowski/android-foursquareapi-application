package konradrutkowski.com.tapapp.places

interface PlaceContract {

    interface View {
        fun showDetails(place: Place)
    }

    interface Presenter {
        fun loadPlaces()
    }


}