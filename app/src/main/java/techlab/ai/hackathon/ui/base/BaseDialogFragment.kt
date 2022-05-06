package techlab.ai.hackathon.ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import techlab.ai.hackathon.R

/**
 * @author BachDV
 */
abstract class BaseDialogFragment : DialogFragment() {
    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog1 = Dialog(requireActivity(), R.style.DialogFragment)
        dialog1.setCancelable(true)
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog1.window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val dialogWelcomeNavigationOnKey = DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
//                TrackingEventImpl.getInstance().sendEventPopupAds(ads, TrackingEventConstant.Value.CLOSE)
                dialog.dismiss()
                dialog.cancel()
                // move other fragment
                return@OnKeyListener true
            }
            false
        }
        dialog1.setOnKeyListener(dialogWelcomeNavigationOnKey)
        dialog1.setOnKeyListener(dialogWelcomeNavigationOnKey)
        val viewContent =  initBindingView()
        dialog1.setContentView(viewContent)
        afterCreatedView(viewContent)
        return dialog1
    }

    abstract fun initBindingView() :View
    abstract fun afterCreatedView( view: View)

}