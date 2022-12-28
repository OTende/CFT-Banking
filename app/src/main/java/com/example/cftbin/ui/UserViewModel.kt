package com.example.cftbin.ui

import androidx.lifecycle.*
import com.example.cftbin.model.User
import com.example.cftbin.model.UserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = _user
    var isOpen = false
    val translated = hashMapOf<String, String>(
        "debit" to "Дебетовая",
        "credit" to "Кредитная",
        "true" to "Да",
        "false" to "Нет"
    )

    var users = listOf<User>()

    fun getUsers() {
        viewModelScope.launch {
            users = userRepository.getUsers()
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.saveUser(user)
        }
    }

    fun getUser(num: Int) {
        viewModelScope.launch {
            try {
                _user.value = userRepository.getUser(num)
            } catch (e: Throwable) {
                _user.value = null
            }
        }
    }

    class Factory(private val repository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(repository) as T
        }
    }
}