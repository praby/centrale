package eu.corellis.centrale.activitylifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        //finish activity and send result
        button2.setOnClickListener {
            val monIntent = Intent().apply {
                putExtra("ret",editTextTextPersonName.text.toString())   //string
            }
            setResult(Activity.RESULT_OK, monIntent)
            finish()
        }
    }
}