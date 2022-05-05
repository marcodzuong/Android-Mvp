package techlab.ai.hackathon.network

import io.reactivex.Observable
import retrofit2.http.GET
import techlab.ai.hackathon.data.model.BaseResponse
import techlab.ai.hackathon.data.model.DemoModel

/**
 * @author BachDV
 * Date:06/05/2022
 */
interface ApiInterface {

    @GET("v1/users/list-friend")
    fun getDemo() : Observable<BaseResponse<DemoModel>>

}