package techlab.ai.hackathon.network

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import techlab.ai.hackathon.cached.SharePref

/**
 * @author BachDV
 */
object ApiClient {

    private var apiInterface: ApiInterface? = null
    fun getInstance(context: Context): ApiInterface {
        if (apiInterface == null) {
            apiInterface = createApiInterface(context)
        }
        return apiInterface!!
    }

    private fun createApiInterface(context: Context): ApiInterface {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConst.API_URL)
            .client(createOkHttpClient(context))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    private fun createOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpHeaderInterceptor())
//            .addInterceptor(
//                HttpLoggingInterceptor.Builder()
//                    .setLevel(HttpLoggingInterceptor.Level.BODY)
//                    .request("Request")
//                    .response("Response")
//                    .build()
//            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })

            .build()
    }
}

class HttpHeaderInterceptor : Interceptor {
    @SuppressLint("HardwareIds")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        request.header("platform", "android")
        if (SharePref.token.isNotEmpty()) {
            request.header("Authorization", "Bearer ${SharePref.token}")
            Log.d("SangToken", SharePref.token)
        }
        return chain.proceed(request.build())
    }

}


