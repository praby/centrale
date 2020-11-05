package eu.corellis.centrale.activitylifecycle

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*


class BoundService : Service() {
    // Binder given to clients
    private val binder: IBinder = LocalBinder()

    // Random number generator
    private val mGenerator = Random()

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): BoundService {
            // Return this instance of LocalService so clients can call public methods
            return  this@BoundService
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    /** method for clients  */
    fun getRandomNumber(): Int {
        return mGenerator.nextInt(100)
    }
}

