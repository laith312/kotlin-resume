package com.alnagem.resume.ui.aboutme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alnagem.resume.R
import com.alnagem.resume.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AboutMeViewModel : ViewModel() {

    val image = MutableLiveData<Int>().apply {
        value = R.drawable.profile_img
    }

    val profileText = MutableLiveData<String>().apply {
        value = ""
    }

    init {
        fetchProfileText()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchProfileText() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = ProfileRepository.fetchProfileIntro()
            withContext(Dispatchers.Main) {
                profileText.value = result
            }
        }
    }
}