package com.task.appiness.ui.activity.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.appiness.data.network.Resource
import com.task.appiness.data.network.response.MyHierarchy
import com.task.appiness.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _hierarchyResponse: MutableLiveData<Resource<MyHierarchy>> = MutableLiveData()
    val hierarchyResponse: LiveData<Resource<MyHierarchy>>
        get() = _hierarchyResponse


    fun getHierarchy() = viewModelScope.launch {
        _hierarchyResponse.value = Resource.Loading
        _hierarchyResponse.value = repository.getHierarchy()
    }


}