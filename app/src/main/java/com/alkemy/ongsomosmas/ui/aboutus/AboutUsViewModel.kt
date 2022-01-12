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
    private val _membersViewState = MutableLiveData<AboutUsState>()
    val membersViewState: LiveData<AboutUsState> = _membersViewState

    fun getMembers() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                getMembersUC()
            }
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    _membersViewState.value = AboutUsState.ShowLoading(false)
                    _membersViewState.value = result.data?.let { AboutUsState.ShowMembersList(it) }
                }
                Resource.Status.ERROR -> {
                    _membersViewState.value = AboutUsState.ShowLoading(false)
                    _membersViewState.value = AboutUsState.ShowError
                }
                Resource.Status.LOADING -> {
                    _membersViewState.value = AboutUsState.ShowLoading(true)
                }
            }

        }

    }


}

sealed class AboutUsState {
    data class ShowMembersList(val listMembers: List<MembersResponse>) : AboutUsState()
    object ShowError : AboutUsState()
    data class ShowLoading(val isLoading: Boolean) : AboutUsState()
}