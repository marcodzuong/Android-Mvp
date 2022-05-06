package techlab.ai.hackathon.cached

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author BachDV
 */
@Suppress("UNCHECKED_CAST")
class SharePrefDelegate<K>(val share : SharedPreferences, val key : String, val defaultValue : K):
    ReadWriteProperty<Any, K> {
    override fun getValue(thisRef: Any, property: KProperty<*>): K {
        return when (defaultValue){
            is String -> share.getString(key,defaultValue ) as K
            is Int -> share.getInt(key,defaultValue as Int) as K
            is Boolean -> share.getBoolean(key,defaultValue as Boolean) as K
            is Float -> share.getFloat(key,defaultValue as Float) as K
            is Long -> share.getLong(key,defaultValue as Long) as K
            is Set<*> -> share.getStringSet(key,defaultValue as Set<String>) as K
            else -> Gson().fromJson(share.getString(key,defaultValue  as String), object : TypeToken<K>(){}.type) }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: K) {
        when(value) {
            is String -> share.edit().putString(key,value).apply()
            is Int -> share.edit().putInt(key, value).apply()
            is Boolean -> share.edit().putBoolean(key, value).apply()
            is Long -> share.edit().putLong(key, value).apply()
            is Float -> share.edit().putFloat(key,value).apply()
            is Set<*> -> share.edit().putStringSet(key,value as HashSet<String>).apply()
            else ->{
                val data = Gson().toJson(value)
                share.edit().putString(key,data).apply()
            }
        }
    }

}