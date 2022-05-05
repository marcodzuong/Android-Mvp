package techlab.ai.hackathon.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import techlab.ai.hackathon.R
import techlab.ai.hackathon.databinding.ActivityLoginBinding
import techlab.ai.hackathon.ui.base.BaseActivity
import techlab.ai.hackathon.ui.main.MainActivity

class LoginActivity : BaseActivity() {

    companion object{
        @JvmStatic
        fun startSelf(context : Context){
            val starter = Intent(context, LoginActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var binding : ActivityLoginBinding

    override fun initBindingView(): View {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun afterCreatedView() {
    }


}