package com.alkemy.ongsomosmas.ui.home.adapter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.TestimonialResponse
import com.alkemy.ongsomosmas.data.testimonials.TestimonialRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestimonialViewModel @ViewModelInject constructor(private val testimonialRepositoryImp: TestimonialRepositoryImp) :
    ViewModel() {
    private val _testimonials = MutableLiveData<Resource<List<TestimonialResponse>>>()
    val testimonials: LiveData<Resource<List<TestimonialResponse>>> = _testimonials

    fun getTestimonials(): Boolean {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                testimonialRepositoryImp.getTestimonials()
            }
            _testimonials.value = result
        }
        return (_testimonials != null)
    }



}