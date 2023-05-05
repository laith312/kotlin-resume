package com.alnagem.resume.repository

import com.alnagem.resume.R
import com.alnagem.resume.ResumeApplication
import org.json.JSONObject

class WorkHistoryRepository {

    private fun getWorkHistoryJSONObject(): JSONObject {
        val fileInputStream = ResumeApplication.appContext.resources.openRawResource(
            R.raw.about_me
        )
        val bytes = ByteArray(fileInputStream.available())
        fileInputStream.read(bytes)
        fileInputStream.close()

        return JSONObject(String(bytes))
    }

}