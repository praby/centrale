package eu.corellis.centrale.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        // read parameters

        val i = intent
        val key1:String? = i.getStringExtra("key1")
        val key2:Boolean = i.getBooleanExtra("key2",false)
        val key3:Int = i.getIntExtra("key3",0)

        Log.d("SecondActivityWithData", ""+key1)

        textView.setText("Key1: "+i.getStringExtra("key1"))
        if (key2) {
            textView2.setText("Key2: true")
        }
        else{
            textView2.setText("Key2: false")

        }
        textView3.setText("Key3: "+key3.toString())
    }
}