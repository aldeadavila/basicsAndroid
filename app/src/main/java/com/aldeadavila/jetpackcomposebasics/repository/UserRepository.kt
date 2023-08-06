package com.aldeadavila.jetpackcomposebasics.repository

import androidx.lifecycle.LiveData
import com.aldeadavila.jetpackcomposebasics.datasource.RestDataSource
import com.aldeadavila.jetpackcomposebasics.model.User
import com.aldeadavila.jetpackcomposebasics.model.UserDao
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser() : User
    suspend fun deleteUser(toDelete:User)
    fun getAllUser(): LiveData<List<User>>
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
): UserRepository {

    override suspend fun getNewUser(): User {
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserLocation().results[0].location!!
        val picture = dataSource.getUserPictures().results[0].picture!!
        val user = User(
            name.title, name.first, name.last, location.city, location.state, picture.thumbnail,
            picture.large, picture.medium
        )
        userDao.insert(user)
        return user
    }

    override suspend fun deleteUser(toDelete: User) = userDao.delete(toDelete)

    override fun getAllUser(): LiveData<List<User>> = userDao.getAll()
}