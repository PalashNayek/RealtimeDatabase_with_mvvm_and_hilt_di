package com.palash.realtimedatabase_with_mvvm_and_hilt_di.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.palash.realtimedatabase_with_mvvm_and_hilt_di.repository.RealtimeDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val repository: RealtimeDatabaseRepository) :
    ViewModel() {

    private val _data = MutableStateFlow<DataSnapshot?>(null)
    val data: StateFlow<DataSnapshot?> get() = _data

    init {
        observeData("Name")
    }

    private fun observeData(path: String) {
        viewModelScope.launch {
            repository.observeData(path) { snapshot ->
                _data.value = snapshot
            }
        }
    }

    fun setData(path: String, value: Any) {
        viewModelScope.launch {
            repository.setData(path, value)
        }
    }
}