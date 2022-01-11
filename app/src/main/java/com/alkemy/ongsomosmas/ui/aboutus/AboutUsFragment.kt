package com.alkemy.ongsomosmas.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.MembersResponse
import com.alkemy.ongsomosmas.databinding.FragmentAboutUsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AboutUsFragment : Fragment() {
    private lateinit var binding: FragmentAboutUsBinding
    private lateinit var aboutUsAdapter: AboutUsAdapter
    private val aboutUsViewModel: AboutUsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)

        aboutUsViewModel.getMembers()

        aboutUsViewModel.members.observe(viewLifecycleOwner, {
            binding.loading.progressBar.isVisible = it.status == Resource.Status.LOADING

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { members ->
                        aboutUsAdapter = AboutUsAdapter(
                            members,
                            onClick = {
                                TODO("implementOnClick")
                            }
                        )

                    }
                    binding.rvMembers.adapter = aboutUsAdapter

                }
                Resource.Status.ERROR -> {
                    Toast.makeText(
                        this.context,
                        getString(R.string.about_us_error),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else -> {}
            }
        })

        return binding.root
    }
}