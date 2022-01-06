package com.alkemy.ongsomosmas.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alkemy.ongsomosmas.data.model.MembersResponse
import com.alkemy.ongsomosmas.databinding.FragmentAboutUsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AboutUsFragment: Fragment() {
    private lateinit var binding: FragmentAboutUsBinding
    private lateinit var aboutUsAdapter: AboutUsAdapter
    private var members = mutableListOf<MembersResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentAboutUsBinding.inflate(inflater,container,false)
        aboutUsAdapter = AboutUsAdapter(
            members,
            onClick = {
//                Toast.makeText(context, "Seleccionaste la posicion $it", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvMembers.adapter = aboutUsAdapter
        return binding.root
    }
}