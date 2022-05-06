package techlab.ai.hackathon.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author BachDV
 */
class BaseListResponse<T>(
    @SerializedName("status_code")
    val status_code: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("data")
    val data: List<T>?
) {
    fun isSuccess(): Boolean = status_code == 200
}