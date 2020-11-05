package eu.corellis.centrale.activitylifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    val ForResultActivity_1 = 100
    val ForResultActivity_2 = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")

        button.setOnClickListener {
            val monIntent = Intent(this, Activity2::class.java)
            startActivityForResult(monIntent, ForResultActivity_1)
        }
        button4.setOnClickListener {
            val monIntent = Intent(this, Activity3::class.java)
            startActivityForResult(monIntent, ForResultActivity_2)
        }
    }


    override fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent?) {
        // Check which request we're responding to
        if (requestCode == ForResultActivity_1){
            Log.d("onActivityResult","first "+resultCode)
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this@MainActivity,"ret: "+data?.getStringExtra("ret"), Toast.LENGTH_SHORT).show()

            }
        }
        if (requestCode == ForResultActivity_2) {
            Log.d("onActivityResult","second "+resultCode)

            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this@MainActivity,"ret: "+data?.getStringExtra("name")+
                        " "+data?.getStringExtra("firstname")
                        , Toast.LENGTH_SHORT).show()
                data?.also {
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}
