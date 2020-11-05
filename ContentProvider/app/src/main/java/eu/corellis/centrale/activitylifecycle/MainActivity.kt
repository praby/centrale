package eu.corellis.centrale.activitylifecycle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCreate Called")

    }

    fun onClickDisplayEntries(view: View) {
        tvDisplayDataHere.text = ""
        var queryUri = Uri.parse("content://eu.corellis.centrale2019.provider/countries").toString()
        var projection = arrayOf("country")
        var selectionClause: String?
        var selectionArgs: Array<String>? = null
        var sortOrder: String? = null
        when (view.id) {
            R.id.tvDisplayAll -> {
                selectionClause = null
                selectionArgs = null
            }
            R.id.tvDisplayFirst -> {
                selectionClause = " id= ?"
                selectionArgs = arrayOf("0")
            }
            else -> {
                selectionClause = null
                selectionArgs = null
            }
        }
        val cursor = contentResolver.query(
                Uri.parse(queryUri), projection, selectionClause,
                selectionArgs, sortOrder)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                //val columnIndex = cursor.getColumnIndex(projection[0])
                do {
                    val word = cursor.getString(0)
                    tvDisplayDataHere.append(word + "\n")
                } while (cursor.moveToNext())
            } else {
                tvDisplayDataHere.append("No data returned." + "\n")
            }
            cursor.close()
        } else {
            tvDisplayDataHere.append("Cursor is null." + "\n")
        }
    }

}
