package konradrutkowski.com.tapapp.online

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.util.ByteArrayBuffer
import java.io.BufferedInputStream

object HTTPResponse {


    fun getResponse(url: String): String {
        var replyString: String? = null
        val httpclient = DefaultHttpClient()

        val httpget = HttpGet(url)

        try {

            val response = httpclient.execute(httpget)
            val `is` = response.entity.content

            val bis = BufferedInputStream(`is`)
            val baf = ByteArrayBuffer(20)
            var current: Int
            while ((current = bis.read()) != -1) {
                baf.append(current.toByte().toInt())
            }

            replyString = String(baf.toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return replyString!!.trim { it <= ' ' }

    }
}
