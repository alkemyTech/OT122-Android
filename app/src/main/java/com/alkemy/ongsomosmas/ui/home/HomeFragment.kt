package com.alkemy.ongsomosmas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialState
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialViewModel
import com.alkemy.ongsomosmas.ui.home.adapter.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.news.NewsAdapter
import com.alkemy.ongsomosmas.ui.home.adapter.TestimonialAdapter
import com.alkemy.ongsomosmas.ui.home.welcome.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.welcome.WelcomeViewModel
import com.alkemy.ongsomosmas.utils.afterTextChanged
import com.alkemy.ongsomosmas.ui.home.adapter.WelcomeAdapter
import com.alkemy.ongsomosmas.ui.home.news.NewsViewModel
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
    private val welcomeViewModel: WelcomeViewModel by viewModels()

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


        welcomeViewModel.welcomeSlide()
        setObserver()



        return binding.root
    }

    private fun setUpObservers(){
        testimonialViewModel.testimonialsViewState.observe(viewLifecycleOwner){
            when(it){
                is TestimonialState.Success -> {
                    setDataAndShowRecycler(it.listTestimonial)
                }
                is TestimonialState.Error -> {
                    binding.rvTestimonial.isVisible=false
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

    private fun setObserver() {
        welcomeViewModel.welcomeResponse.observe(viewLifecycleOwner, Observer {
            binding.loading.progressBar.isVisible = it.status == Resource.Status.LOADING

            when (it.status) {
                Resource.Status.SUCCESS -> {
                        it.data?.let { item ->
                            welcomeAdapter = WelcomeAdapter(
                                item
                            )
                        }
                    binding.rvSlides.adapter = welcomeAdapter
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this.context, getString(R.string.home_error), Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }

        })
    }
}

