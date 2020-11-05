package eu.corellis.centrale.activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    var editTextInput:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")
        editTextInput = findViewById(R.id.edit_text_input);

    }

    fun startService(v: View?) {
        val input = editTextInput!!.text.toString()
        val serviceIntent = Intent(this, ForegroundService::class.java)
        serviceIntent.putExtra("inputExtra", input)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(v: View?) {
        val serviceIntent = Intent(this, ForegroundService::class.java)
        stopService(serviceIntent)
    }
}
