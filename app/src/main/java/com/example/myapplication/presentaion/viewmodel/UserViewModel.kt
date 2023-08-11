package com.example.myapplication.presentaion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import com.example.notes.Utils.Resource
import com.example.notes.Utils.SingleLiveEvent
import com.example.notes.data.enums.UserGender
import com.example.notes.data.local.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var userRepository: UserRepository) : ViewModel() {

    val usersLiveData = SingleLiveEvent<Resource<List<User>>>()
    val addUserLiveData = SingleLiveEvent<Resource<Long>>()
    val clearDBLiveData = SingleLiveEvent<Resource<Any>>()
    val newUserEnabled = SingleLiveEvent<Boolean>()
    private val newUserName = MutableLiveData<String>()
    private val newUserAge = MutableLiveData<String>()
    private val newUserJob = MutableLiveData<String>()
    private val newUserGender = MutableLiveData<UserGender>()


    init {
        setNewUserButtonEnabled(false)
    }


    fun getUsers() = viewModelScope.launch {
        try {
            usersLiveData.postValue(Resource.Loading())
            usersLiveData.postValue(Resource.Success(userRepository.getUsers()))
        } catch (ex: Exception) {
            usersLiveData.postValue(Resource.Error(ex.message.toString()))
        }
    }

    fun addUser() = viewModelScope.launch {
        try {
            addUserLiveData.postValue(Resource.Loading())
            addUserLiveData.postValue(
                Resource.Success(
                    userRepository.addUser(
                        User(
                            newUserName.value.toString().trim(),
                            newUserAge.value.toString().trim().toInt(),
                            newUserJob.value.toString().trim(),
                            newUserGender.value ?: UserGender.UNIDENTIFIED
                        )
                    )
                )
            )
        } catch (ex: Exception) {
            addUserLiveData.postValue(Resource.Error(ex.message.toString()))
        }
    }

    fun clearDB() = viewModelScope.launch {
        try {
            clearDBLiveData.postValue(Resource.Loading())
            clearDBLiveData.postValue(Resource.Success(userRepository.clearUsers()))
        } catch (ex: Exception) {
            clearDBLiveData.postValue(Resource.Error(ex.localizedMessage))
        }
    }

    private fun setNewUserButtonEnabled(isEnabled: Boolean? = null) {
        newUserEnabled.postValue(
            isEnabled
                ?: (newUserName.value?.isNotEmpty() == true
                        && newUserJob.value?.isNotEmpty() == true
                        && newUserAge.value?.toString()?.isNotEmpty() == true)
        )
    }

    fun setUserName(userName: String) {
        newUserName.value = userName
        setNewUserButtonEnabled()
    }

    fun setUserAge(age: Int) {
        newUserAge.value = age.toString()
        setNewUserButtonEnabled()
    }

    fun setUserJob(job: String) {
        newUserJob.value = job
        setNewUserButtonEnabled()
    }

    fun setUserGender(gender: UserGender) {
        newUserGender.value = gender
        setNewUserButtonEnabled()
    }

}