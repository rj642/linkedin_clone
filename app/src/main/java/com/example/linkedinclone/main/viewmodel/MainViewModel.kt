package com.example.linkedinclone.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.linkedinclone.common.network.Resource
import com.example.linkedinclone.common.repository.DummyJsonRepo
import com.example.linkedinclone.main.model.PostSchema
import com.example.linkedinclone.main.model.RESPONSE
import com.example.linkedinclone.main.model.UserModel
import com.example.linkedinclone.main.model.Users
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

    private val _allUsers = MutableLiveData<List<Users>>()

    private val _refAllUsers = MutableLiveData<List<Users>>()

    private val _postsLoading = MutableLiveData<RESPONSE>()

    private val _usersLoading = MutableLiveData<RESPONSE>()

    private val _homeLoading = MutableLiveData<RESPONSE>()

    private val _jobsLoading = MutableLiveData<RESPONSE>()

    private val _notificationLoading = MutableLiveData<RESPONSE>()

    private val _userData = MutableLiveData<List<Map<Int, String>>>()

    private val userDataRefList = mutableListOf<Map<Int, String>>()

    val userData
        get() = _userData

    val allPost
        get() = _allPost

    val allUser
        get() = _allUsers

    val postsLoading
        get() = _postsLoading

    val usersLoading
        get() = _usersLoading

    val refAllUsers
        get() = _refAllUsers

    val homeLoading
        get() = _homeLoading

    val jobLoading
        get() = _jobsLoading

    val notificationLoading
        get() = _notificationLoading

    init {
        _postsLoading.value = RESPONSE.IDLE
        _usersLoading.value = RESPONSE.IDLE
        _notificationLoading.value = RESPONSE.IDLE
        _homeLoading.value = RESPONSE.IDLE
        _jobsLoading.value = RESPONSE.IDLE
        _userData.value = userDataRefList
        fetchAllPost()
        getAllUsers()
    }

    private val hashMap = HashMap<Int, String>()

    fun updateUserFullName() = if (_allPost.value?.posts?.isNotEmpty() == true) {
        hashMap.clear()
        _allPost.value?.posts?.let {
            for (post in it) {
                _allUsers.value?.let { users ->
                    if (!hashMap.containsKey(post.userId)) {
                        val value = users.find { user -> user.id == post.userId }
                        hashMap[post.userId] = "${value?.firstName} ${value?.lastName}"
                    }
                }
            }
            userDataRefList.clear()
            userDataRefList.add(hashMap)
            _userData.value = userDataRefList
        }
    } else {
        userDataRefList.add(hashMap)
        _userData.value = userDataRefList
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
                                    _allUsers.value = response.users
                                    _refAllUsers.value = response.users
                                    updateUserFullName()
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

    fun filterUserList(search: String) {
        _allUsers.value = _refAllUsers.value
        _allUsers.value = _allUsers.value?.let {
            it.filter { users -> users.firstName.lowercase().contains(search) }
        }
    }

}