package techlab.ai.hackathon.cached

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import techlab.ai.hackathon.MyApplication

/**
 * @author BachDV
 * Date:06/05/2022
 */
object SharePref {

    private const val FILE_NAME = "petory.share.data"

    private var prefs: SharedPreferences =
        MyApplication.mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    var token: String by SharePrefDelegate(prefs, "token", "")
    var userName: String by SharePrefDelegate(prefs, "userName", "")



    @SuppressLint("CommitPrefEdits")
    fun clear() {
        prefs.edit().clear().commit()
    }
}