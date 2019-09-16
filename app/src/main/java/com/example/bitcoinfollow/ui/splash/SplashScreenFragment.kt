package com.example.bitcoinfollow.ui.splash


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bitcoinfollow.R

/**
 * A simple [Fragment] subclasss.
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({

            findNavController().navigate(R.id.action_splashScreenFragment_to_home)

        }, SPLASH_DELAY_MILLIS.toLong())


    }

    companion object {
        val SPLASH_DELAY_MILLIS = 3 * 1000
    }
}
