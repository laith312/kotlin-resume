package com.alnagem.resume.ui.workhistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alnagem.resume.data.ResumeDataBase
import com.alnagem.resume.data.WorkHistory
import com.alnagem.resume.repository.ProfileRepository
import com.alnagem.resume.repository.WorkHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var workHistoryRepository: WorkHistoryRepository
    private lateinit var profileRepository: ProfileRepository

    val workHistory = MutableLiveData<List<WorkHistory>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val workHistoryDAO = ResumeDataBase.getDatabase(application).workHistoryDAO()
            workHistoryRepository = WorkHistoryRepository(workHistoryDAO)
            val profileDAO = ResumeDataBase.getDatabase(application).profileDAO()
            profileRepository = ProfileRepository(profileDAO)

            fetchWorkHistory()
        }
    }

    private suspend fun fetchWorkHistory() {
        val profile = profileRepository.fetchProfile()
        val history = workHistoryRepository.fetchWorkHistoryByProfileId(profile.id)
        withContext(Dispatchers.Main) {
            workHistory.value = history
        }
    }
}