package com.alkemy.ongsomosmas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.NewsResponse
import com.alkemy.ongsomosmas.data.model.WelcomeResponse
import com.alkemy.ongsomosmas.databinding.FragmentHomeBinding
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialViewModel
import com.alkemy.ongsomosmas.ui.home.adapter.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.news.NewsAdapter
import com.alkemy.ongsomosmas.ui.home.testimonials.TestimonialAdapter
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

        newsAdapter = NewsAdapter(
            news,
            onClickLastItem = {
                //TODO
            })

        binding.rvNews.adapter = newsAdapter

        if(testimonialViewModel.getTestimonials()){
            testimonialViewModel.testimonials.observe(viewLifecycleOwner, {

                it.data?.let { testimonial ->
                    testimonialAdapter = TestimonialAdapter(
                        testimonial,
                        onClick = {
                            //TODO
                        },
                        onClickLastItem = {
                            //TODO
                        })
                }
                binding.rvTestimonial.adapter = testimonialAdapter
            })
        }

        else{
            binding.containerTestimonials.visibility = View.GONE;
        }

        welcomeAdapter = WelcomeAdapter(welcome)
        binding.rvSlides.adapter = welcomeAdapter


        return binding.root
    }
}

