package com.shikimori.common.viewmodel

import androidx.lifecycle.ViewModel as AndroidViewModel
import androidx.lifecycle.viewModelScope as androidViewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class ViewModel : AndroidViewModel() {
    actual val viewModelScope: CoroutineScope = androidViewModelScope
    
    actual override fun onCleared() {
        super.onCleared()
    }
} 