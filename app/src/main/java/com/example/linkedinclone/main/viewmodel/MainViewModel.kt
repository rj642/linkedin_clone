package com.example.linkedinclone.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkedinclone.common.network.Resource
import com.example.linkedinclone.common.repository.DummyJsonRepo
import com.example.linkedinclone.main.model.PostSchema
import com.example.linkedinclone.main.model.RESPONSE
import com.example.linkedinclone.main.model.UserModel
import com.example.linkedinclone.utils.Extensions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: DummyJsonRepo) : ViewModel() {

    private val _allPost = MutableLiveData<PostSchema>()

    private val _allUsers = MutableLiveData<UserModel>()

    private val _postsLoading = MutableLiveData<RESPONSE>()

    private val _usersLoading = MutableLiveData<RESPONSE>()

    val allPost
        get() = _allPost

    val allUser
        get() = _allUsers

    val postsLoading
        get() = _postsLoading

    val usersLoading
        get() = _usersLoading

    init {
        _postsLoading.value = RESPONSE.IDLE
        _usersLoading.value = RESPONSE.IDLE
        fetchAllPost()
        getAllUsers()
    }

    fun fetchAllPost() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.getAllPost().collectLatest {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            withContext(Dispatchers.Main) {
                                _postsLoading.value = RESPONSE.LOADING
                            }
                        }
                        Resource.Status.SUCCESS -> {
                            withContext(Dispatchers.Main) {
                                _postsLoading.value = RESPONSE.SUCCESS
                                it.data?.let { response ->
                                    _allPost.value = response
                                }
                            }
                        }
                        Resource.Status.ERROR -> {
                            withContext(Dispatchers.Main) {
                                _postsLoading.value = RESPONSE.FAILED
                            }
                        }
                    }
                }
            }
        }

    fun getAllUsers() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.getAllUsers().collectLatest {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            withContext(Dispatchers.Main) {
                                _usersLoading.value = RESPONSE.LOADING
                            }
                        }
                        Resource.Status.SUCCESS -> {
                            withContext(Dispatchers.Main) {
                                _usersLoading.value = RESPONSE.SUCCESS
                                it.data?.let { response ->
                                    _allUsers.value = response
                                }
                            }
                        }
                        Resource.Status.ERROR -> {
                            withContext(Dispatchers.Main) {
                                _usersLoading.value = RESPONSE.FAILED
                                Extensions.log("error occurred while fetching data ${it.message}")
                            }
                        }
                    }
                }
            }
        }

}