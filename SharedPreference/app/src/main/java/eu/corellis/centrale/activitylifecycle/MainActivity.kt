package eu.corellis.centrale.activitylifecycle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")

        button.setOnClickListener {
            val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener

            with (sharedPref.edit()) {
                putInt(getString(R.string.saved_high_score_key), 1000)
                apply()
            }
        }

        button2.setOnClickListener {
            val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return@setOnClickListener

            val highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), 0)
            Toast.makeText(applicationContext, "highScore:"+highScore, Toast.LENGTH_LONG).show()
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
