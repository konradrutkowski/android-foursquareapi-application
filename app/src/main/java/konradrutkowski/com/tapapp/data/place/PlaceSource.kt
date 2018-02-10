package konradrutkowski.com.tapapp.data.place

interface PlaceSource {

    fun loadPlaces()

    fun loadPlace(placeId: String)

}