package eu.corellis.centrale.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
        val TAG="MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log.info("OnCreate Called")

        val btn = button
        button.setOnClickListener ( this )

        val btn2 = button2
        button2.setOnClickListener ( this )
    }

    override fun onStart() {
        super.onStart()
        log.info("onStart Called")
    }

    override fun onRestart() {
        super.onRestart()
        log.info("OnRestart Called")
    }

    override fun onResume() {
        super.onResume()
        log.info("OnResume Called")
    }


    override fun onStop() {
        super.onStop()
        log.info("OnStop Called")
    }

    override fun onPause() {
        super.onPause()
        log.info("OnPause Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        log.info("OnDestroy Called")
    }

    override fun onClick(v: View?) {
        if (v==button){
            log.info("Button 1 clicked")
        }
        if (v==button2){
            log.info("Button 2 clicked")
        }
    }


}
