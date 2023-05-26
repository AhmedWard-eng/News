package com.example.news.data.repo

import com.example.news.data.local.preferences.FackUserManager
import com.example.news.data.local.preferences.LocalUser
import com.example.news.data.remote.datasource.FackAuthRemoteDataSource
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Test

class AuthRepoImpTest {

    private var authRepoImp: AuthRepoImp? = null
    private lateinit var fackUserManager : FackUserManager

    @Before
    fun getSetUp() {
        fackUserManager = FackUserManager()
        authRepoImp = AuthRepoImp(FackAuthRemoteDataSource(), fackUserManager)
    }

    @After
    fun tarDown() {
        authRepoImp = null
    }

    @Test
    fun signInSucces() = runBlockingTest {
        val user = authRepoImp?.login("email", "password")

        assertThat(user, IsEqual(true))
    }

    @Test
    fun signInFailer() = runBlockingTest {
        val user = authRepoImp?.login("ema", "passwo")

        assertThat(user, IsEqual(false))
    }

    @Test
    fun signUp() = runBlockingTest {
        val user = authRepoImp?.signUP()

        assertThat(user, IsEqual(true))
    }

    @Test
    fun logout() = runBlockingTest {
        val user = authRepoImp?.logout()

        assertThat(user, IsEqual(true))
    }

    @Test
    fun saveLoggedInData() = runBlockingTest {
        authRepoImp?.saveLoggedInData(LocalUser("id", "userName", "email"))

        assertThat(fackUserManager.userId, IsEqual("id"))
        assertThat(fackUserManager.userName, IsEqual("userName"))
        assertThat(fackUserManager.email, IsEqual("email"))

    }

}

