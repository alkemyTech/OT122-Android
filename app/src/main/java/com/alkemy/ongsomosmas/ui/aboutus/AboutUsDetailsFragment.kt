package com.alkemy.ongsomosmas.ui.aboutus

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


        binding.aboutUsDetailsTextviewName.text = "María garcia"
        binding.aboutUsDetailsTextviewJob.text = "Logistica"
        binding.aboutUsDetailsTextviewAdmissionDate.text = "28/05/21"
        binding.aboutUsDetailsTextviewNetworkFacebook.text = "https://www.facebook.com/"
        binding.aboutUsDetailsTextviewNetworkLinkeding.text = "https://www.linkedin.com/feed"
    }
}