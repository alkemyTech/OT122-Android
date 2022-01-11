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

    private val _allNews = MutableLiveData<List<NewsResponse>>()
    val allNews: LiveData<List<NewsResponse>> = _allNews

    fun getNews() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                newsRepositoryImp.getNews()
            }
            _news.value = result
        }
    }

    fun getAllNews() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO){
                newsRepositoryImp.getNews()
            }.run {
                when(status){
                    Resource.Status.SUCCESS -> {
                        data?.let {
                            _allNews.value = it
                        }
                    }
                    Resource.Status.ERROR -> {
                        _allNews.value = emptyList()
                    }
                    else -> {}
                }
            }

        }
    }

}