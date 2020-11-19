package eu.corellis.centrale.activitylifecycle

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    var CHANNEL_ID = "eu.corellis.centrale.activitylifecycle"
    var notificationId = 1
    var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")
        createNotificationChannel()
        button.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId++, builder.build())
            }

        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"OnRestart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume Called")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"OnPause Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"OnDestroy Called")
    }


}
