package com.example.news.data.repo

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
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

