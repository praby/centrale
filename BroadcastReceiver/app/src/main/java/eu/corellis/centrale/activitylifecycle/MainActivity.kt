package eu.corellis.centrale.activitylifecycle

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //1. Create an instance of BroadcastReceiver.
    val br: BroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")

        button.setOnClickListener {
            Intent().also { intent ->
                intent.setAction("com.example.myapplication.MY_NOTIFICATION")
                intent.putExtra("data", "Call button")
                sendBroadcast(intent)
            }
        }


        //System broadcast
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction("com.example.myapplication.MY_NOTIFICATION")
        }

        //Custom broadcast
        /*val filter = IntentFilter("com.example.myapplication").apply {

        }*/
        registerReceiver(br, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"OnDestroy Called")

        //3. stop receiving broadcasts
        unregisterReceiver(br)
    }


}
