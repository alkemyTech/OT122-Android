package com.alkemy.ongsomosmas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.NewsResponse
import com.alkemy.ongsomosmas.data.model.TestimonialResponse
import com.alkemy.ongsomosmas.data.model.WelcomeResponse
import com.alkemy.ongsomosmas.databinding.FragmentHomeBinding
import com.alkemy.ongsomosmas.ui.home.adapter.NewsAdapter
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialAdapter
import com.alkemy.ongsomosmas.ui.home.welcome.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.welcome.WelcomeViewModel
import com.alkemy.ongsomosmas.utils.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var welcomeAdapter: WelcomeAdapter
    private lateinit var testimonialAdapter: TestimonialAdapter

    // Datos de prueba. Eliminar cuando se implemente la integración con la api
    private var news = mutableListOf<NewsResponse>()
    private var testimonial = mutableListOf<TestimonialResponse>()
    //private var welcome = mutableListOf<WelcomeResponse>()
    private val welcomeViewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        newsAdapter = NewsAdapter(
            news,
            onClickLastItem = {
                //TODO
            })

        binding.rvNews.adapter = newsAdapter

        //welcomeAdapter = WelcomeAdapter(welcome)
        //binding.rvSlides.adapter = welcomeAdapter
        setObserver()

        testimonialAdapter = TestimonialAdapter(
            testimonial,
            onClick = {
                //TODO
            },
            onClickLastItem = {
                //TODO
            })

        binding.rvTestimonial.adapter = testimonialAdapter

        return binding.root
    }

    private fun setObserver() {
        welcomeViewModel.welcomeResponse.observe(viewLifecycleOwner, Observer {

            binding.loading.progressBar.isVisible = it.status == Resource.Status.LOADING

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val data =
                        it.data?.map { item -> WelcomeResponse(
                            item.name,
                            item.content,
                            item.image,
                        ) } ?: emptyList()
                    binding.rvSlides.adapter = WelcomeAdapter(data)
                }
                Resource.Status.ERROR -> {
                }
                else -> {
                }
            }

        })
    }
}

