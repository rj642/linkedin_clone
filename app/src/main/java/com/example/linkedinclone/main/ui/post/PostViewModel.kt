package com.example.linkedinclone.main.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkedinclone.common.network.Resource
import com.example.linkedinclone.common.repository.DummyJsonRepo
import com.example.linkedinclone.main.model.PostSchema
import com.example.linkedinclone.utils.Extensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repo: DummyJsonRepo) : ViewModel() {

    enum class RESPONSE {
        IDLE,
        LOADING,
        SUCCESS,
        FAILED
    }

    private val _allPost = MutableLiveData<PostSchema>()

    private val _dataLoading = MutableLiveData<RESPONSE>()

    val allPost
        get() = _allPost

    init {
        _dataLoading.value = RESPONSE.IDLE
        fetchAllPost()
    }

    fun fetchAllPost() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllPost().collectLatest {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        withContext(Dispatchers.Main) {
                            _dataLoading.value = RESPONSE.LOADING
                        }
                    }
                    Resource.Status.SUCCESS -> {
                        withContext(Dispatchers.Main) {
                            _dataLoading.value = RESPONSE.SUCCESS
                        }
                        it.data?.let { response ->
                            withContext(Dispatchers.Main) {
                                _allPost.value = response
                                Extensions.log("Response received from backend is\n$response")
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        withContext(Dispatchers.Main) {
                            _dataLoading.value = RESPONSE.FAILED
                        }
                    }
                }
            }
        }
    }

}