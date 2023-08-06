package com.aldeadavila.jetpackcomposebasics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeadavila.jetpackcomposebasics.model.User
import com.aldeadavila.jetpackcomposebasics.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel(){

    //Variable que usamos mientras responde el rest. Será false hasta que responda que será true
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val users: LiveData<List<User>> by lazy {
        userRepo.getAllUser()
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addUser() {
        if (_isLoading.value ==  false)
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepo.getNewUser()
                _isLoading.postValue(false)
            }
    }

    fun deleteUser(toDelete:User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepo.deleteUser(toDelete)
        }
    }

    //función para testear el rest
    /*fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepositoryImp.getNewUser()
            Log.d("UserViewModel", user.toString())
        }
    }*/
}