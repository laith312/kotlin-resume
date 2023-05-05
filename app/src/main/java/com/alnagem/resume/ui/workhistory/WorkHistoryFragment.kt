package com.alnagem.resume.ui.workhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.alnagem.resume.databinding.FragmentWorkHistoryBinding


class WorkHistoryFragment : Fragment() {

    private var _binding: FragmentWorkHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkHistoryBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[WorkHistoryViewModel::class.java]
        val workHistoryAdapter = WorkHistoryAdapter()

        viewModel.workHistory.observe(viewLifecycleOwner) {
            workHistoryAdapter.workHistoryData = it
        }

        binding.recyclerView.adapter = workHistoryAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context, VERTICAL, false)

        return binding.root
    }
}