package techlab.ai.hackathon.ui.main

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import techlab.ai.hackathon.ui.base.BaseController

/**
 * @author BachDV
 */
class MainController(context: Context,private val mainView: MainView) : BaseController(context) {

    fun callApi() {
       addDisposable(
           retrofit.getDemo()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                   {
                       it.data?.let {model->
                           mainView.callBack(model)
                       }
                   }, {
                       it.printStackTrace()
                   }
               )
       )
    }

}