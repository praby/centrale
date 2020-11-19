package eu.corellis.centrale.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.logging.Logger
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    //private lateinit var dataset: Array<String>
    private lateinit var dataset: ArrayList<UserDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset()

        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomAdapter(dataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private fun initDataset() {
        //dataset = Array(DATASET_COUNT, {i -> "This is element # $i"})

        dataset = ArrayList<UserDto>()

        for (i in 0..DATASET_COUNT) {
            var user: UserDto = UserDto("Bett"+i, "Awesome work ;)")
            dataset.add(user)
        }
    }

    companion object {
        val TAG = "RecyclerViewActivity"
        private val DATASET_COUNT = 200
    }
}
