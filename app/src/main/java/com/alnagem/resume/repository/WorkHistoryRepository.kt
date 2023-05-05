package com.alnagem.resume.repository

import android.util.Log
import com.alnagem.resume.R
import com.alnagem.resume.ResumeApplication
import com.alnagem.resume.data.WorkHistory
import com.alnagem.resume.data.WorkHistoryDAO
import org.json.JSONObject

class WorkHistoryRepository(private val workHistoryDAO: WorkHistoryDAO) {
    private val TAG = "WorkHistoryRepository"

    /*
     * Attempt to fetch the profile's work history from the DB. If the work history does not
     * exist in the database the work history will be pulled from the resources R.raw.jobs.json
     * file, then added to the DB.
     */
    suspend fun fetchWorkHistoryByProfileId(profileId: Int): List<WorkHistory> {
        workHistoryDAO.fetchWorkHistory(profileId).let {
            if (it.isNotEmpty()) {
                Log.d(TAG, "WorkHistory found in DB")
                return it
            } else {
                Log.d(TAG, "WorkHistory NOT found")
            }
        }

        // No records Found, add profile to DB from R.raw.about_me
        val workHistory: List<WorkHistory> = getWorkHistoryJSONObject(profileId)
        workHistoryDAO.addWorkHistory(workHistory)
        Log.d(TAG, "Record added to DB")
        return workHistoryDAO.fetchWorkHistory(profileId)
    }

    private fun getWorkHistoryJSONObject(profileId: Int): List<WorkHistory> {
        val fileInputStream = ResumeApplication.appContext.resources.openRawResource(
            R.raw.jobs
        )
        val bytes = ByteArray(fileInputStream.available())
        fileInputStream.read(bytes)
        fileInputStream.close()

        val json = JSONObject(String(bytes)).getJSONArray("jobs")

        val result = mutableListOf<WorkHistory>()
        for (i in 0 until json.length()) {
            result.add(
                WorkHistory(
                    id = 0,
                    profileId = profileId,
                    companyName = json.getJSONObject(i).getString("company"),
                    startDate = json.getJSONObject(i).getString("startDate"),
                    endDate = json.getJSONObject(i).getString("endDate"),
                    position = json.getJSONObject(i).getString("title"),
                    keyTechnologies = json.getJSONObject(i).getString("keyTechnologies"),
                    description = json.getJSONObject(i).getString("description")
                )
            )
        }

        return result
    }

}