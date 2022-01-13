package com.alkemy.ongsomosmas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alkemy.ongsomosmas.data.model.NewsResponse
import com.alkemy.ongsomosmas.data.model.TestimonialResponse
import com.alkemy.ongsomosmas.data.model.WelcomeResponse
import com.alkemy.ongsomosmas.databinding.FragmentHomeBinding
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialAdapter
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialState
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialViewModel
import com.alkemy.ongsomosmas.ui.home.adapter.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.news.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var welcomeAdapter: WelcomeAdapter
    private lateinit var testimonialAdapter: TestimonialAdapter

    private val testimonialViewModel: TestimonialViewModel by viewModels()

    // Datos de prueba. Eliminar cuando se implemente la integración con la api
    private var news = mutableListOf<NewsResponse>()
    private var welcome = mutableListOf<WelcomeResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpObservers()
        testimonialViewModel.getTestimonials()

        newsAdapter = NewsAdapter(
            news,
            onClickLastItem = {
                //TODO
            })
        binding.rvNews.adapter = newsAdapter


        welcomeAdapter = WelcomeAdapter(welcome)
        binding.rvSlides.adapter = welcomeAdapter



        return binding.root
    }

    private fun setUpObservers() {
        testimonialViewModel.testimonialsViewState.observe(viewLifecycleOwner) {
            when (it) {
                is TestimonialState.Success -> {
                    setDataAndShowRecycler(it.listTestimonial)
                }
                is TestimonialState.Error -> {
                    binding.rvTestimonial.isVisible = false
                }
                is TestimonialState.Loading -> {
                    showLoading(it.isLoading)
                }
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        //TODO implements loading
    }

    private fun setDataAndShowRecycler(listTestimonial: List<TestimonialResponse>) {
        testimonialAdapter = TestimonialAdapter(listTestimonial,
            onClick = {}, onClickLastItem = {})
        binding.rvTestimonial.adapter = testimonialAdapter
    }
}

