package techlab.ai.hackathon.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import techlab.ai.hackathon.R
import techlab.ai.hackathon.data.model.DemoModel
import techlab.ai.hackathon.databinding.ActivityMainBinding
import techlab.ai.hackathon.ui.base.BaseActivity

class MainActivity : BaseActivity(), MainView {

    companion object{
        @JvmStatic
        fun startSelf(context : Context){
            val starter = Intent(context,MainActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainController: MainController

    override fun initBindingView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun afterCreatedView() {
        mainController = MainController(this,this)
        mainController.callApi()
    }

    override fun callBack(model: DemoModel) {
        TODO("Not yet implemented")
    }
}