package com.alkemy.ongsomosmas.ui.aboutus


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.databinding.FragmentAboutUsDetailsBinding


class AboutUsDetailsFragment : Fragment(R.layout.fragment_about_us_details) {

    private lateinit var binding: FragmentAboutUsDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutUsDetailsBinding.bind(view)


        binding.aboutUsDetailsTextviewNetworkFacebook.setOnClickListener {
            openLink(binding.aboutUsDetailsTextviewNetworkFacebook.text.toString())
        }

        binding.aboutUsDetailsTextviewNetworkLinkeding.setOnClickListener {
            openLink(binding.aboutUsDetailsTextviewNetworkLinkeding.text.toString())
        }

    }

    fun openLink(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}