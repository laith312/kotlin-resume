package com.alnagem.resume.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.alnagem.resume.R
import com.alnagem.resume.ResumeApplication
import com.alnagem.resume.data.Profile
import com.alnagem.resume.data.ProfileDAO
import org.json.JSONObject

class ProfileRepository(private val profileDAO: ProfileDAO) {
    private val TAG = "ProfileRepository"
    private val dbProfiles: List<Profile> = profileDAO.getAllProfiles()

    suspend fun addProfileToDB(profile: Profile) {
        profileDAO.addProfile(profile)
    }

    /*
     * Attempt to fetch the profile's intro from the DB. If the profile does not exist in the database
     * the profile will be pulled from the resources R.raw.about_me.json file, then added to
     * the DB.
     */
    @WorkerThread
    suspend fun fetchProfile(): Profile {
        // check DB for entry
        dbProfiles.let {
            if (it.isNotEmpty()) {
                Log.d(TAG, "Record found in DB")
                return it[0]
            } else {
                Log.d(TAG, "No records found in the DB")
            }
        }

        // No records Found, add profile to DB from R.raw.about_me
        val profile: Profile = getProfileJSONObject()
        profileDAO.addProfile(profile)
        Log.d(TAG, "Record added to DB")

        return profile
    }

    private fun getProfileJSONObject(): Profile {
        val fileInputStream = ResumeApplication.appContext.resources.openRawResource(
            R.raw.about_me
        )
        val bytes = ByteArray(fileInputStream.available())
        fileInputStream.read(bytes)
        fileInputStream.close()

        val json = JSONObject(String(bytes))

        return Profile(
            id = 0,
            name = json.getString("name") as String,
            email = json.getString("email") as String,
            intro = json.getString("profile_intro") as String,
            phone = json.getString("phone") as String
        )
    }
}