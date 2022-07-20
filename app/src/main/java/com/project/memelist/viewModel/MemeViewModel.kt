package com.project.memelist.viewModel

import android.content.ContentValues.TAG
import android.icu.text.LocaleDisplayNames
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.memelist.api.MemeRepository
import com.project.memelist.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class MemeViewModel(private val repository: MemeRepository,
                    private val dispatcher: CoroutineDispatcher): ViewModel() {

    private val _memeListData = MutableLiveData<UIState>()
    val memeListData: LiveData<UIState> get() = _memeListData

            private val coroutineExceptionHandler by lazy {
                CoroutineExceptionHandler { coroutineContext, throwable ->
                    Log.e(TAG,  "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}", throwable)
                }
            }

    private val viewModelStateScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getDememes(num: Int) {
        viewModelStateScope.launch(dispatcher) {
            //collect from our flow
            repository.getMemesByPageNum(num).collect { state ->
                // postvalue updates livedata asychronously
                _memeListData.postValue(state)
            }
        }
    }

    // setValue is not asynchronous
    fun setLoading(){_memeListData.value = UIState.Loading}
}