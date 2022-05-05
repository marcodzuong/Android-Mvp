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
    var userAvt: String by SharePrefDelegate(prefs, "userAvt", "")
    var userID: String by SharePrefDelegate(prefs, "USER_ID", "")
    var displayName : String by SharePrefDelegate(prefs, "displayName", "")
    var isLogin : Boolean by SharePrefDelegate(prefs, "isLogin", false)
    var tokenSocial : String by SharePrefDelegate(prefs,"tokenSocial", "")
    var listProvince : String by SharePrefDelegate(prefs,"listProvince", "")
    var listTribeDog : String by SharePrefDelegate(prefs,"listTribeDog", "")
    var listTribeCat : String by SharePrefDelegate(prefs,"listTribeCat", "")
    var listSpecies : String by SharePrefDelegate(prefs,"listSpecies", "")
    var listCountry : String by SharePrefDelegate(prefs,"listCountry", "")
    var numPhone : String by SharePrefDelegate(prefs,"numPhone", "")
    var email : String by SharePrefDelegate(prefs,"email", "")
    var province : String by SharePrefDelegate(prefs,"province", "")
    var provinceID : String by SharePrefDelegate(prefs,"provinceID", "")
    var FCMToken : String by SharePrefDelegate(prefs,"FCMToken", "")
    var numHotline : String by SharePrefDelegate(prefs,"numHotline", "0346 032 032")
    var shouldReloadHome : Boolean by SharePrefDelegate(prefs,"shouldReloadHome", false)
    var shouldCheckLoginHome : Boolean by SharePrefDelegate(prefs,"shouldCheckLoginHome", false)


    @SuppressLint("CommitPrefEdits")
    fun clear() {
        prefs.edit().clear().commit()
    }
}