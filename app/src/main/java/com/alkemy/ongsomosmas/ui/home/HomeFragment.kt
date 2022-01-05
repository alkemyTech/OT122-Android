package com.alkemy.ongsomosmas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.NewsResponse
import com.alkemy.ongsomosmas.data.model.TestimonialResponse
import com.alkemy.ongsomosmas.data.model.WelcomeResponse
import com.alkemy.ongsomosmas.databinding.FragmentHomeBinding
import com.alkemy.ongsomosmas.ui.home.news.NewsAdapter
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialAdapter
import com.alkemy.ongsomosmas.ui.home.adapter.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var welcomeAdapter: WelcomeAdapter
    private lateinit var testimonialAdapter: TestimonialAdapter

    private val newsViewModel: NewsViewModel by viewModels()

    // Datos de prueba. Eliminar cuando se implemente la integración con la api
    private var news = mutableListOf<NewsResponse>()
    private var testimonial = mutableListOf<TestimonialResponse>()
    private var welcome = mutableListOf<WelcomeResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Get News
        newsViewModel.getNews()

        newsViewModel.news.observe(viewLifecycleOwner, {
            binding.loading.progressBar.isVisible = it.status == Resource.Status.LOADING

            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { news ->
                        newsAdapter = NewsAdapter(
                            news,
                            onClickLastItem = {
                                //TODO
                            })
                    }
                    binding.rvNews.adapter = newsAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this.context, getString(R.string.home_error), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {}
            }
        })

        welcomeAdapter = WelcomeAdapter(welcome)
        binding.rvSlides.adapter = welcomeAdapter

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
}

