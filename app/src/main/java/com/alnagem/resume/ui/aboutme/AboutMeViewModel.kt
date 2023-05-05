package com.alnagem.resume.ui.aboutme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alnagem.resume.R
import com.alnagem.resume.data.ResumeDataBase
import com.alnagem.resume.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AboutMeViewModel(application: Application) : AndroidViewModel(application) {

    val image = MutableLiveData<Int>().apply {
        value = R.drawable.profile_img
    }

    val profileText = MutableLiveData<String>().apply {
        value = ""
    }

    private lateinit var repository: ProfileRepository

    init {
        val profileDAO = ResumeDataBase.getDatabase(application).profileDAO()
        viewModelScope.launch(Dispatchers.IO) {
            repository = ProfileRepository(profileDAO)
            fetchProfileText()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun fetchProfileText() {
        val result = repository.fetchProfile()
        withContext(Dispatchers.Main) {
            profileText.value = result.intro
        }
    }
}
