package techlab.ai.hackathon.common

import android.app.ActivityManager
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.text.Html
import android.text.SpannableString
import android.text.format.DateUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import io.reactivex.Single
import techlab.ai.hackathon.R
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * @author BachDV
 */
fun ImageView.load(url: String? = "", placeholder: Int = R.drawable.ic_home_top_bg) {
    Glide.with(context)
        .asBitmap().load(url).centerCrop()
        .placeholder(placeholder).override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(this)
}

fun ImageView.loadGif(url: String? = "", drawable: Int) {
    Glide.with(context)
        .asGif()
        .load(drawable)
        .placeholder(drawable)
        .override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .into(this);
}

fun TextView.loadHtml(html: String?) {
    text = when {
        html == null -> {
            SpannableString("")
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        }
        else -> {
            Html.fromHtml(html)
        }
    }
}

fun ImageView.loadCorner(
    url: String? = "",
    corner: Int,
    placeholder: Int = R.drawable.ic_home_top_bg
) {
    val requestOptions = RequestOptions()
    requestOptions.centerCrop()
    requestOptions.transform(CenterCrop(), RoundedCorners(corner))
//        requestOptions.placeholder(R.drawable.placeholder_image);
    requestOptions.placeholder(placeholder)
    requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
    Glide.with(context)
        .asBitmap().load(url).centerCrop()
        .apply(requestOptions)
        .override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(this)
}

fun ImageView.loadImageUri(uri: Any) {
    Glide.with(this.context)
        .load(uri)
        .into(this)
}

fun Context.makeCall(phone: String?) {
    try {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this.startActivity(intent)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}


fun ImageView.load(url: GlideUrl, placeholder: Int = R.drawable.ic_home_top_bg) {
    Glide.with(context)
        .asBitmap().load(url).fitCenter()
        .placeholder(placeholder).override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .into(this)
}

fun ImageView.loadAny(url: Any, placeholder: Int = R.drawable.ic_home_top_bg) {
    Glide.with(context)
        .asBitmap().load(url).fitCenter()
        .placeholder(placeholder).override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .into(this)
}

fun ImageView.loadNoPlaceHolder(url: Any) {
    Glide.with(context)
        .asBitmap().load(url).centerCrop()
        .override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .into(this)
}


fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadGallery(url: String) {
    Glide.with(context)
        .asBitmap().load(url).centerCrop().override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .into(this)
}

fun ImageView.loadIcon(url: Int) {
    Glide.with(context)
        .asBitmap().load(url).centerCrop()
        .override(width, height).diskCacheStrategy(
            DiskCacheStrategy.ALL
        )
        .into(this)

}

//fun ImageView.loadImagePath(url: String? = "", placeholder: Int = R.drawable.avt_pet_demo) {
fun ImageView.loadImagePath(url: String? = "") {
    Glide.with(context)
        .asBitmap().load(url)
        .diskCacheStrategy(
            DiskCacheStrategy.ALL
        ).into(this)
}


fun checkEmailInvalid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun checkDateIsFuture(date: String): Boolean {
    var enteredDate: Date? = null
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    enteredDate = sdf.parse(date)

    val currentDate = Date()
    enteredDate?.let {
        return enteredDate.after(currentDate)
    }
    throw java.lang.Exception()
}

fun checkDateIsPast(date: String): Boolean {
    var enteredDate: Date? = null
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    enteredDate = sdf.parse(date)

    val currentDate = Date()
    enteredDate?.let {
        if (DateUtils.isToday(enteredDate.time)) {
            return false
        }
        return enteredDate.before(currentDate)
    }
    throw java.lang.Exception()
}


fun checkDateIsFutureV2(date: String): Boolean {
    var enteredDate: Date? = null
    val sdf = SimpleDateFormat("dd/MM/yyyy/hh:mm")
    enteredDate = sdf.parse(date)

    val currentDate = Date()
    enteredDate?.let {
        return enteredDate.after(currentDate)
    }
    throw java.lang.Exception()
}


fun selectDate(context: Context?, min: Date?, max: Date?, textView: TextView) {
    showDatePickerDialog(context, min, max, object : OnDateSetListener {
        override fun onDateSet(year: Int, month: Int, dayOfMonth: Int, calendar: Calendar) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val dateStr = dateFormat.format(calendar.time)
            textView.text = dateStr
        }
    })
}

fun showDatePickerDialog(
    context: Context?,
    min: Date?,
    max: Date?,
    listener: OnDateSetListener
): DatePickerDialog {
    return showDatePickerDialog(context, min?.time ?: -1, max?.time ?: -1, listener)
}

fun showDatePickerDialog(
    context: Context?,
    min: Long,
    max: Long,
    listener: OnDateSetListener
): DatePickerDialog {
    return showDatePickerDialog(context, Calendar.getInstance(), min, max, listener)
}

fun showDatePickerDialog(
    context: Context?,
    focusDay: Calendar,
    min: Long,
    max: Long,
    listener: OnDateSetListener
): DatePickerDialog {
    val year = focusDay[Calendar.YEAR]
    val month = focusDay[Calendar.MONTH]
    val dayOfMonth = focusDay[Calendar.DAY_OF_MONTH]
    val dialog = DatePickerDialog(context!!, { view, year, month, dayOfMonth ->
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        c[Calendar.DAY_OF_MONTH] = dayOfMonth
        listener.onDateSet(year, month, dayOfMonth, c)
    }, year, month, dayOfMonth)
    if (min != -1L) dialog.datePicker.minDate = min
    if (max != -1L) dialog.datePicker.maxDate = max
    dialog.show()
    return dialog
}

fun Context.isAppInBackgrounds(): Single<Boolean> = Single.create { emitter ->
    var isInBackground = true
    val am = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningProcesses = am.runningAppProcesses
    if (runningProcesses == null) emitter.onSuccess(true)
    else {
        var filer1 = runningProcesses.filter {
            it.importance === ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && !it.pkgList.filter { it == packageName }
                .isNullOrEmpty()
        }
        emitter.onSuccess(filer1.isNullOrEmpty())
    }
}


fun validCellPhone(number: String?): Boolean {
    return PHONE1.matcher(number).matches()
}

val PHONE1 = Pattern.compile("^(09|03|05|07|08)+([0-9]{8})\$")

val Int.pxInt: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


fun Context.showTimeDiaLog(timeEdt: TextView) {
    val mCurrentTime = Calendar.getInstance()
    val hour = mCurrentTime[Calendar.HOUR_OF_DAY]
    val minute = mCurrentTime[Calendar.MINUTE]
    val mTimePicker = TimePickerDialog(
        this,
        TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            val datetime = Calendar.getInstance()
            val c = Calendar.getInstance()
            datetime[Calendar.HOUR_OF_DAY] = selectedHour
            datetime[Calendar.MINUTE] = selectedMinute
            if (c.timeInMillis <= datetime.timeInMillis) {
                val hStg = if (selectedHour <= 9) {
                    "0$selectedHour"
                } else {
                    selectedHour
                }

                val mStg = if (selectedMinute <= 9) {
                    "0$selectedMinute"
                } else {
                    selectedMinute
                }
                timeEdt.text = "$hStg:$mStg"
            } else {
//                AppToast.createToast(ToastStyle.ERROR, ).setText("Vui lòng chọn thời gian trong tương lai").show(timeEdt.context)
            }
        }, hour, minute, true
    )

    mTimePicker.setTitle("Select Time")
    mTimePicker.show()
}

interface OnDateSetListener {
    fun onDateSet(year: Int, month: Int, dayOfMonth: Int, calendar: Calendar)
}

operator fun <T> Intent.set(key: String, value: T) {
    when (value) {
        is String -> this.putExtra(key, value)
        is Boolean -> this.putExtra(key, value)
        is Int -> this.putExtra(key, value)
        is Short -> this.putExtra(key, value)
        is Long -> this.putExtra(key, value)
        is Byte -> this.putExtra(key, value)
        is ByteArray -> this.putExtra(key, value)
        is Char -> this.putExtra(key, value)
        is CharArray -> this.putExtra(key, value)
        is CharSequence -> this.putExtra(key, value)
        is Float -> this.putExtra(key, value)
        is Parcelable -> this.putExtra(key, value)
        is Serializable -> this.putExtra(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

inline operator fun <reified T : Any> Intent.get(key: String, defaultValue: T? = null): T? {
    return when (T::class) {
        String::class -> getStringExtra(key) as T?
        Int::class -> getIntExtra(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBooleanExtra(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloatExtra(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLongExtra(key, defaultValue as? Long ?: -1) as T?
        else -> Gson().fromJson(getStringExtra(key), T::class.java)
    }
}


class TimerScope {
    var isCanceled: Boolean = false
        private set

    fun cancel() {
        isCanceled = true
    }
}


fun EditText.showSoftKeyboard() {
    if (this.requestFocus()) {
        val imm: InputMethodManager =
            this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun EditText.hideSoftKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}