package techlab.ai.hackathon.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import techlab.ai.hackathon.R
import techlab.ai.hackathon.databinding.FragmentHomeBinding
import techlab.ai.hackathon.ui.base.BaseFragment


class HomeFragment : BaseFragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun initBindingView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun afterCreatedView() {
    }

}