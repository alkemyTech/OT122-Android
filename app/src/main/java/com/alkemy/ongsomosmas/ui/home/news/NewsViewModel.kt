package com.alkemy.ongsomosmas.ui.home.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.home.news.NewsRepositoryImp
import com.alkemy.ongsomosmas.data.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel @ViewModelInject constructor(private val newsRepositoryImp: NewsRepositoryImp) :
    ViewModel() {
        private val _news = MutableLiveData<Resource<List<NewsResponse>>>()
        val news: LiveData<Resource<List<NewsResponse>>> = _news

        fun getNews() {
            viewModelScope.launch(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) {
                    newsRepositoryImp.getNews()
                }
                _news.value = result
            }
        }
}