package com.example.cftbin.ui

import androidx.lifecycle.*
import com.example.cftbin.model.User
import com.example.cftbin.model.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = _user
    var isOpen = false
    val translated = hashMapOf(
        "debit" to "Дебетовая",
        "credit" to "Кредитная",
        "true" to "Да",
        "false" to "Нет"
    )

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        updateUsers()
    }

    fun updateUsers() {
        viewModelScope.launch {
            // Я не смог придумать варианта лучше
            delay(500)
            _users.value = userRepository.getUsers()
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