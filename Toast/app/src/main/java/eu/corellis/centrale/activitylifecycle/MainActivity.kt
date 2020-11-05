package eu.corellis.centrale.activitylifecycle

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {


    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        log.info("OnCreate Called")

        button.setOnClickListener {
            Toast.makeText(applicationContext, "Toast simple", Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener {
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container))
            val text: TextView = layout.findViewById(R.id.text)
            text.text="Vous avez cliqué sur le bouton "
            with(Toast(applicationContext)){
                setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                duration= Toast.LENGTH_SHORT
                view = layout
                show()
            }
        }

        button3.setOnClickListener {
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@MainActivity)

            // Set the alert dialog title
            builder.setTitle("App background color")

            // Display a message on alert dialog
            builder.setMessage("Are you want to set the app background color to RED?")

            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("YES"){dialog, which ->
                // Do something when user press the positive button
                Toast.makeText(applicationContext,"Ok, we change the app background.",Toast.LENGTH_SHORT).show()

                // Change the app background color
                root_layout.setBackgroundColor(Color.RED)
            }


            // Display a negative button on alert dialog
            builder.setNegativeButton("No"){dialog,which ->
                Toast.makeText(applicationContext,"You are not agree.",Toast.LENGTH_SHORT).show()
            }


            // Display a neutral button on alert dialog
            builder.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(applicationContext,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }
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


}
