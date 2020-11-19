package eu.corellis.centrale.activitylifecycle

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.android.volley.Network
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.*
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    val url: String = "https://jsonplaceholder.typicode.com/posts"
    //val url: String = "https://newsapi.org/v2/sources?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button10.setOnClickListener {
            getPostsWithTask()
        }

        button7.setOnClickListener {
            getPostsWithoutThread()
        }

        button8.setOnClickListener {
            getPostsWithAsyntask().execute()
        }

        button9.setOnClickListener {
            getPostsWithVolley()
        }
    }

    fun getPostsWithoutThread() {

        var str: String = ""
        try {
            str = URL(url).readText(
                    Charsets.UTF_8
            )

            Toast.makeText(this, "1-Fetched Data: "+str, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "1-error: "+e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun getPostsWithTask() {
        Thread{
            var str: String? = ""
            try {
                val theUrl = URL(url)
                str  = downloadUrl(theUrl)
                //str = URL(url).readText(Charsets.UTF_8)

                /*Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(this@MainActivity, "2-Fetched Data: "+str, Toast.LENGTH_SHORT).show()
                }, 3000)*/

                runOnUiThread({
                    //Update UI
                    Toast.makeText(this, "2-Fetched Data: "+str, Toast.LENGTH_SHORT).show()
                })
            } catch (e: Exception) {
                Log.d("Fetched Data", "error: "+e.toString())
            }
        }.start()
    }

    /*
     * function for network call with Volley library
     * call: getUsersWithVolley()
     */
    fun getPostsWithVolley() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->

                    var strResp = response.toString()
                    val jsonArray: JSONArray = JSONArray(strResp)
                    var strPosts: String = ""
                    for (i in 0 until jsonArray.length()) {
                        var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                        strPosts = strPosts + "\n" + jsonInner.get("title")
                    }
                    Toast.makeText(this, "3-Fetched Data: "+strResp, Toast.LENGTH_SHORT).show()
                    Log.d("3-Fetched Data", strResp)
                },
                Response.ErrorListener {
                    Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
                    Log.d("Fetched Data","That didn't work!")
                }
        )
        queue.add(stringReq)
    }

    /*
     * call: getPostsWithAsyntask().execute()
     */
    inner class getPostsWithAsyntask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var str: String = ""
            try {
                str = URL(url).readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                Log.d("Fetched Data ", "error: "+e.toString())
            }
            return str
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Toast.makeText(this@MainActivity, "4-Fetched Data: "+result, Toast.LENGTH_SHORT).show()
            Log.d("Fetched Data ", result)
        }
    }

    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in String form. Otherwise,
     * it will throw an IOException.
     */
    @Throws(IOException::class)
    private fun downloadUrl(url: URL): String? {
        var connection: HttpsURLConnection? = null
        return try {
            connection = (url.openConnection() as? HttpsURLConnection)
            connection?.run {
                // Timeout for reading InputStream arbitrarily set to 3000ms.
                readTimeout = 3000
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                connectTimeout = 3000
                // For this use case, set HTTP method to GET.
                requestMethod = "GET"
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                doInput = true
                // Set Headers
                setRequestProperty("User-Agent","Mozilla/5.0")
                setRequestProperty("Content-Type","application/json")
                // Open communications link (network traffic occurs here).
                connect()

                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw IOException("HTTP error code: $responseCode")
                }
                // Retrieve the response body as an InputStream.
                inputStream?.let { stream ->
                    // Converts Stream to String with max length of 500.
                    readStream(stream, 500)
                }
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            connection?.inputStream?.close()
            connection?.disconnect()
        }
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    @Throws(IOException::class, UnsupportedEncodingException::class)
    fun readStream(stream: InputStream, maxReadSize: Int): String? {
        val reader: Reader? = InputStreamReader(stream, "UTF-8")
        val rawBuffer = CharArray(maxReadSize)
        val buffer = StringBuffer()
        var readSize: Int = reader?.read(rawBuffer) ?: -1
        var maxReadBytes = maxReadSize
        while (readSize != -1 && maxReadBytes > 0) {
            if (readSize > maxReadBytes) {
                readSize = maxReadBytes
            }
            buffer.append(rawBuffer, 0, readSize)
            maxReadBytes -= readSize
            readSize = reader?.read(rawBuffer) ?: -1
        }
        return buffer.toString()
    }
}
