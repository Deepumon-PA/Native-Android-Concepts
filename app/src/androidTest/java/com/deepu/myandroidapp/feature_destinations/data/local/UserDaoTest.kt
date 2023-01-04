package com.deepu.myandroidapp.feature_destinations.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting.User
import com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting.UserDao
import com.deepu.myandroidapp.feature_destinations.data.local.classesForTesting.UserDataBase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

//tests should run synchronously (not asynchronously), ie all operations are recommended to run on the same thread
//since there is a possibility of manipulation of data by other threads when testing
//in most cases test is performed on the main thread

// integration tests: tests how app components work together (ie viewModel and fragment)
//integrated tests: tests android specific components , example:  instrumentation test
@RunWith(AndroidJUnit4::class)
@SmallTest // integrated test: mediumTest, UI: Large Test
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule =  InstantTaskExecutorRule()

    lateinit var userDatabase: UserDataBase
    lateinit var userDao: UserDao

    @Before
    fun setUp(){
        userDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDataBase::class.java,
           ).allowMainThreadQueries().build()
        userDao = userDatabase.userDao()
    }


    @Test
    fun insertUser() = runBlockingTest {  //optimized runBlocking function for testing: will skip delay, availability of functions like pause/resume dispatcher, advance time
        val user1 = User(1, "Deepu","", null)
        userDao.insertUser(user1)
        assertThat(userDao.getUserById(user1.userId)).isEqualTo(user1)
    }

    @Test
    fun replaceUser_returns_true() = runBlockingTest {
        val user1  = User(1, "Deepu", "", null)
        val user2  = User(2, "Ben", "", null)
        val user3  = User(3, "Ajai", "", null)

        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        userDao.deleteUser(2)
        val user4  = User(2, "Sebastian", "", null)
        userDao.insertUser(user4)

        assertThat(userDao.getUserById(2)).isEqualTo(user2)
    }

    @Test
    fun userListContainsAnItem_isTrue() = runBlockingTest{
        val user1  = User(1, "Deepu", "", null)
        val user2  = User(2, "Ben", "", null)
        val user3  = User(3, "Ajai", "", null)
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        val allUsers = userDao.getAllUsers().getOrAwaitValue() // Gets the value of a [LiveData] or waits for it to have one, with a timeout. since live data is asynchronous, making it synchronous by this way.
        assertThat(allUsers).contains(user3)


    }

}