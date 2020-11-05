package eu.corellis.centrale.activitylifecycle

import android.net.Uri

class Contract  {
    companion object {
        val AUTHORITY  =     "eu.corellis.centrale2019.provider"
        val CONTENT_PATH = "countries"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
        val ALL_ITEMS = -2
        val WORD_ID = "id"
    }
}