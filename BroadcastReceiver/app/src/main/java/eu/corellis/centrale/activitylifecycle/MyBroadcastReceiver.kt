package eu.corellis.centrale.activitylifecycle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }

    /*
    override fun onReceive(context: Context?, intent: Intent?) {
      if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action) {
         val noConnectivity: Boolean =
            intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
         if (noConnectivity) {
            Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
         } else {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
         }
      }
   }
     */
}
