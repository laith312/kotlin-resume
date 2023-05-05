package com.alnagem.resume.ui.workhistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alnagem.resume.R
import com.alnagem.resume.data.WorkHistory

class WorkHistoryAdapter : RecyclerView.Adapter<WorkHistoryAdapter.WorkHistoryViewHolder>() {

    private var _workHistoryData: List<WorkHistory> = emptyList()

    @get:Deprecated(message = "no.", level = DeprecationLevel.ERROR)
    var workHistoryData: List<WorkHistory> = _workHistoryData
        set(newData) {
            _workHistoryData = newData
            notifyDataSetChanged()
            field = newData
        }

    class WorkHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyTitle: TextView
        val date: TextView
        val positionName: TextView
        val skills: TextView
        val jobDesc: TextView

        init {
            companyTitle = view.findViewById(R.id.title)
            date = view.findViewById(R.id.date)
            positionName = view.findViewById(R.id.positionName)
            skills = view.findViewById(R.id.skills)
            jobDesc = view.findViewById(R.id.jobDescription)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_work_experience, parent, false)

        return WorkHistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _workHistoryData.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: WorkHistoryViewHolder, position: Int) {
        viewHolder.companyTitle.text = _workHistoryData[position].companyName
        viewHolder.positionName.text = _workHistoryData[position].position
        viewHolder.skills.text = _workHistoryData[position].keyTechnologies
        viewHolder.jobDesc.text = _workHistoryData[position].description
        viewHolder.date.text =
            "${_workHistoryData[position].startDate} - ${_workHistoryData[position].endDate}"
    }
}