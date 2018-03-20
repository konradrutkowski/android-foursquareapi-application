package konradrutkowski.com.tapapp.service

import retrofit2.Retrofit


class Service() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/")
            .build()
    }

    val foursquareService by lazy { retrofit.create(FoursquareAPI::class.java) }

    fun getPlaces() {
        foursquareService.listRepos(mapOf())
    }

}