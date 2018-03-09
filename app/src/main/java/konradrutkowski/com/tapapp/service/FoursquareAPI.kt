package konradrutkowski.com.tapapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface FoursquareAPI {

    @GET("$API_VERSION/$VENUES/$SEARCH")
    fun listRepos(@QueryMap parameters: Map<String, String>): Call<List<FoursquareAPI>>



    companion object {
        const val API_VERSION = "v2"
        const val VENUES = "venues"
        const val SEARCH = "search"
    }
}