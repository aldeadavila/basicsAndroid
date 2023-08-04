package com.aldeadavila.jetpackcomposebasics.repository

import com.aldeadavila.jetpackcomposebasics.datasource.RestDataSource
import com.aldeadavila.jetpackcomposebasics.model.User
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser() : User
}

class UserRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
): UserRepository {

    override suspend fun getNewUser(): User {
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserLocation().results[0].location!!
        val picture = dataSource.getUserPictures().results[0].picture!!
        return User(
            name.title, name.first, name.last, location.city, location.state, picture.thumbnail,
            picture.large, picture.medium
        )
    }
}