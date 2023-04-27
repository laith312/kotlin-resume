package com.alnagem.resume.repository

import com.alnagem.resume.R
import com.alnagem.resume.ResumeApplication
import com.alnagem.resume.model.Profile
import org.json.JSONObject

class ProfileRepository {

    companion object {

        fun fetchProfileIntro(): String {
            val fileInputStream = ResumeApplication.appContext.resources.openRawResource(
                R.raw.about_me
            )
            val bytes: ByteArray = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            fileInputStream.close()

            val json: JSONObject = getProfileJSONObject()

            if (json.has("profile_intro")) {
                return json.getString("profile_intro") as String
            }

            return ""
        }

        fun fetchProfile(): Profile {
            val jsonProfile = getProfileJSONObject()
            return Profile(
                name = jsonProfile.getString("name"),
                email = jsonProfile.getString("email"),
                phone = jsonProfile.getString("phone"),
                intro = jsonProfile.getString("profile_intro")
            )
        }

        private fun getProfileJSONObject(): JSONObject {
            val fileInputStream = ResumeApplication.appContext.resources.openRawResource(
                R.raw.about_me
            )
            val bytes = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            fileInputStream.close()

            val json = JSONObject(String(bytes))

            return json
        }
    }


}