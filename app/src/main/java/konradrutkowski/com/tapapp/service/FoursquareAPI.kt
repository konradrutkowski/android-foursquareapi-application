package konradrutkowski.com.tapapp.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface FoursquareAPI {

    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String): Call<List<FoursquareAPI>>

    @GET("users/{user}/repos")
  //  fun listRepos(@Path("user") user: String): Call<List<Repo>>
}