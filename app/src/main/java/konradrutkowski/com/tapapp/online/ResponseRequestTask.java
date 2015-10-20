package konradrutkowski.com.tapapp.online;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ResponseRequestTask {


    public static String makeCall(String latitude, String longtitude) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        String url;
        url = RequestConfig.REQUEST_LINK + RequestConfig.CLIENT_ID + "&client_secret=" + RequestConfig.CLIENT_SECRET + "&v=20130815&ll="
                + latitude + "," + longtitude + "&categoryId=4d4b7105d754a06374d81259";
        return HTTPResponse.getResponse(url);
    }

    public static String makeCall(String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDateandTime = sdf.format(new Date());
        String url;
        url = "https://api.foursquare.com/v2/venues/" + id + "/photos?client_id=" + RequestConfig.CLIENT_ID + "&client_secret=" + RequestConfig.CLIENT_SECRET + "&v=20130815";
        return HTTPResponse.getResponse(url);
    }

}

