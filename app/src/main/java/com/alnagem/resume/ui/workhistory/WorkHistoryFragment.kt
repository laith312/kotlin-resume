package com.alnagem.resume.ui.workhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alnagem.resume.databinding.FragmentWorkHistoryBinding

class WorkHistoryFragment : Fragment() {

    private var _binding: FragmentWorkHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkHistoryBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[WorkHistoryViewModel::class.java]

//        viewModel.workHistory.observe(viewLifecycleOwner, {
//            binding.recyclerView.adapter.data = it
//        })

        return binding.root
    }

}