package techlab.ai.hackathon

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication

/**
 * @author BachDV
 */
class MyApplication: MultiDexApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext : Context
        var appInBackground = false
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}