package konradrutkowski.com.tapapp.online;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class HTTPResponse {


    public static String getResponse(String url) {
        String replyString = null;
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);

        try {

            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }

            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return replyString.trim();

    }
}
