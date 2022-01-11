package com.alkemy.ongsomosmas.ui.aboutus

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.aboutus.AboutUsRepositoryImp
import com.alkemy.ongsomosmas.data.model.MembersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AboutUsViewModel @ViewModelInject constructor(
    private val getMembersUC: GetMembersUseCase


) :
    ViewModel() {
    private val _members = MutableLiveData<Resource<List<MembersResponse>>>()
    val members: LiveData<Resource<List<MembersResponse>>> = _members

    fun getMembers() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                getMembersUC()
            }
            _members.value = result
        }
    }
}