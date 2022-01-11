package com.alkemy.ongsomosmas.ui.home.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        //Get all news
        newsViewModel.getAllNews()

        newsViewModel.allNews.observe(viewLifecycleOwner, { newsList ->
            if (newsList.isEmpty()){
                with(binding){
                    newsFragmentError.root.isVisible = true
                    newsFragmentError.tvError.text = getString(R.string.news_error_fragment)
                    newsFragmentError.btnRetry.setOnClickListener {
                        newsViewModel.getAllNews()
                        newsFragmentError.root.isVisible = false
                    }
                }
            }
        })

        return binding.root
    }
}