package konradrutkowski.com.tapapp.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import konradrutkowski.com.tapapp.fsquare.FSquarePlace;

public class Parser {
    public static ArrayList<FSquarePlace> parseFoursquare(final String response) {

        ArrayList<FSquarePlace> temp = new ArrayList<>();
        try {


            JSONObject jsonObject = new JSONObject(response);
            Log.i("TRY", "TRY");

            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("venues")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");
                    Log.i("TRY", "TRY");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        FSquarePlace poi = new FSquarePlace();
                        if (jsonArray.getJSONObject(i).has("id")) {
                            Log.i("ID", jsonArray.getJSONObject(i).getString("id"));
                            poi.setID(jsonArray.getJSONObject(i).getString("id"));

                            if (jsonArray.getJSONObject(i).has("name")) {
                                poi.setName(jsonArray.getJSONObject(i).getString("name"));
                                if (jsonArray.getJSONObject(i).has("location")) {
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("address")) {
                                        poi.setAddress(jsonArray.getJSONObject(i).getJSONObject("location").getString("address"));
                                        if (jsonArray.getJSONObject(i).getJSONObject("location").has("distance")) {
                                            poi.setDistance(jsonArray.getJSONObject(i).getJSONObject("location").getString("distance"));
                                            if (jsonArray.getJSONObject(i).getJSONObject("location").has("city")) {
                                                poi.setCity(jsonArray.getJSONObject(i).getJSONObject("location").getString("city"));
                                            }
                                        }
                                        if (jsonArray.getJSONObject(i).has("categories")) {
                                            if (jsonArray.getJSONObject(i).getJSONArray("categories").length() > 0) {
                                                if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                                                    poi.setCategory(jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name"));
                                                }
                                            }


                                        }

                                        if (jsonArray.getJSONObject(i).has("stats")) {
                                            if (jsonArray.getJSONObject(i).getJSONObject("stats").has("checkinsCount")) {
                                                poi.setCheckins(jsonArray.getJSONObject(i).getJSONObject("stats").getString("checkinsCount"));
                                            }
                                        }
                                    }
                                    temp.add(poi);
                                }
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return temp;
    }

    public static String parsePictureURL(final String response) {

        String size = "400x400";
        String prefix = null;
        String suffix = null;

        try {

            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("photos")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONObject("photos").getJSONArray("items");
                    if (jsonArray.getJSONObject(0).has("prefix")) {
                        prefix = (jsonArray.getJSONObject(0).getString("prefix"));
                        if (jsonArray.getJSONObject(0).has("suffix")) {
                            suffix = (jsonArray.getJSONObject(0).getString("suffix"));
                        }
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return prefix + size + suffix;
    }
}

