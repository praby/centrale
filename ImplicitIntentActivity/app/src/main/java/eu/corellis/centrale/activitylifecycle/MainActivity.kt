package eu.corellis.centrale.activitylifecycle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")

        /*button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.google.com"))

            // Always use string resources for UI text.
            // This says something like "Share this photo with"
            val title: String = resources.getString(R.string.chooser_title)
            // Create intent to show the chooser dialog
            val chooser: Intent = Intent.createChooser(intent, title)

            // Verify the original intent will resolve to at least one activity
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        }*/
        button.setOnClickListener {
            // Always use string resources for UI text.
            // This says something like "Share this text with"
            val title: String = resources.getString(R.string.chooser_title)
            // Create intent to show the chooser dialog
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, title)
            // Verify the original intent will resolve to at least one activity
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            }
        }

        button2.setOnClickListener {
            //  Display the phone dialer with the given number filled in
            //val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+33123456789"))
            //  Display the phone dialer with the person filled in
            //val intent = Intent(Intent.ACTION_DIAL, Uri.parse("content://contacts/people/1"))
            //  Display information about the person whose identifier is "1"
            //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"))
            //   Display a list of people, which the user can browse through.
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"))

            startActivity(intent)
        }

        /*
        button2.setOnClickListener {
            //Launch the home screen.
            val intent = Intent(Intent.ACTION_MAIN).also { it->
                it.addCategory(Intent.CATEGORY_HOME)
            }
            startActivity(intent)
        }*/

        /*
        button2.setOnClickListener {
            //Send Email
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:youremail@gmail.com") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            startActivity(intent)
        }
         */

        /*button2.setOnClickListener {
            //To capture an image
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
         */
        /*button2.setOnClickListener {
            //display a map of San Francisco
            val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
        }*/

        /*
        Here, "mode=b" is for bicycle.
        We can set driving, walking, and bicycling mode by using:
        d for driving
        w for walking
        b for bicycling
         */
        /*button2.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q="+destintationLatitude+","+destintationLongitude + "&mode=b")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }*/


    }
}
