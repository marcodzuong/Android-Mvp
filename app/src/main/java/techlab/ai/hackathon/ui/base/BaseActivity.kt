package techlab.ai.hackathon.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author BachDV
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initBindingView())
        afterCreatedView()
    }

    abstract fun initBindingView() : View
    abstract fun afterCreatedView()
}