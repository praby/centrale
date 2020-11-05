package eu.corellis.centrale.activitylifecycle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_3.*

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        //finish activity and send result
        button3.setOnClickListener {
            val monIntent = Intent().apply {
                putExtra("name",editTextText2.text.toString())   //string
                putExtra("firstname",editTextText3.text.toString())   //string
            }
            setResult(Activity.RESULT_OK, monIntent)
            finish()
        }
    }
}
