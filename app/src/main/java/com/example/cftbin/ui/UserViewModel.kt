package com.example.cftbin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cftbin.model.User
import com.example.cftbin.model.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getUser(num: Int) {
        viewModelScope.launch {
            _user.value = userRepository.getUser(num)
        }
    }

    companion object {
        class Factory(private val repository: UserRepository) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(repository) as T
            }
        }
    }
}