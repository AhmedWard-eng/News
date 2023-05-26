package com.example.news.data.repo

import com.example.news.data.repo.auth.AuthRepoImp
import org.junit.After
import org.junit.Before

class AuthRepoImpTest{
    private var authRepoImp : AuthRepoImp? = null
    @Before
    fun getSetUp(){
        authRepoImp = AuthRepoImp()
    }
    @After
    fun tarDown(){
        authRepoImp = null
    }


}

