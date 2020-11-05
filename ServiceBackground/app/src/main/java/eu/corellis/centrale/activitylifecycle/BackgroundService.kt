package eu.corellis.centrale.activitylifecycle

import android.app.IntentService
import android.content.Intent
import android.util.Log


class BackGroundService: IntentService("BackGroundService") {
/**
 * A constructor is required, and must call the super
 * `[IntentService(String)](/reference/android/app/IntentService.html#IntentService(java.lang.String))`
 * constructor with a name for the worker thread.
 */

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    override fun onHandleIntent(intent: Intent?) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            // Gets data from the incoming Intent
            val dataString = intent!!.dataString
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            // Restore interrupt status.
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BackGroundService", "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("BackGroundService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("BackGroundService", "onCreate")
    }
}
