package eu.corellis.centrale.activitylifecycle

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    var mService: BoundService? = null
    var mBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    /** Called when a button is clicked (the button in the layout file attaches to
     * this method with the android:onClick attribute)  */
    fun onButtonClick(v: View?) {
        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            val num: Int = mService!!.getRandomNumber()
            Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
        }
    }

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName,
                                        service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder: BoundService.LocalBinder = service as BoundService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }
}
