package konradrutkowski.com.tapapp.util

import android.util.Log
import konradrutkowski.com.tapapp.places.Place
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object Parser {
    fun parseFoursquare(response: String): ArrayList<Place> {

        val temp = ArrayList<Place>()
        try {


            val jsonObject = JSONObject(response)
            Log.i("TRY", "TRY")

            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("venues")) {
                    val jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues")
                    Log.i("TRY", "TRY")
                    for (i in 0 until jsonArray.length()) {
                        val poi = Place()
                        if (jsonArray.getJSONObject(i).has("id")) {
                            Log.i("ID", jsonArray.getJSONObject(i).getString("id"))
                            poi.id = jsonArray.getJSONObject(i).getString("id")

                            if (jsonArray.getJSONObject(i).has("name")) {
                                poi.name = jsonArray.getJSONObject(i).getString("name")
                                if (jsonArray.getJSONObject(i).has("location")) {
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("address")) {
                                        poi.address = jsonArray.getJSONObject(i).getJSONObject("location").getString("address")
                                        if (jsonArray.getJSONObject(i).getJSONObject("location").has("distance")) {
                                            poi.distance = jsonArray.getJSONObject(i).getJSONObject("location").getString("distance")
                                            if (jsonArray.getJSONObject(i).getJSONObject("location").has("city")) {
                                                poi.city = jsonArray.getJSONObject(i).getJSONObject("location").getString("city")
                                            }
                                        }
                                        if (jsonArray.getJSONObject(i).has("categories")) {
                                            if (jsonArray.getJSONObject(i).getJSONArray("categories").length() > 0) {
                                                if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                                                    poi.category = jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name")
                                                }
                                            }


                                        }

                                        if (jsonArray.getJSONObject(i).has("stats")) {
                                            if (jsonArray.getJSONObject(i).getJSONObject("stats").has("checkinsCount")) {
                                                poi.checkins = jsonArray.getJSONObject(i).getJSONObject("stats").getString("checkinsCount")
                                            }
                                        }
                                    }
                                    temp.add(poi)
                                }
                            }

                        }
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return ArrayList()
        }

        return temp
    }

    fun parsePictureURL(response: String): String {

        val size = "400x400"
        var prefix: String? = null
        var suffix: String? = null

        try {

            val jsonObject = JSONObject(response)

            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("photos")) {
                    val jsonArray = jsonObject.getJSONObject("response").getJSONObject("photos").getJSONArray("items")
                    if (jsonArray.getJSONObject(0).has("prefix")) {
                        prefix = jsonArray.getJSONObject(0).getString("prefix")
                        if (jsonArray.getJSONObject(0).has("suffix")) {
                            suffix = jsonArray.getJSONObject(0).getString("suffix")
                        }
                    }
                }

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return prefix + size + suffix
    }
}

