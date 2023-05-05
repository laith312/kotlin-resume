package com.alnagem.resume.ui.aboutme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alnagem.resume.databinding.FragmentAboutMeBinding

class AboutMeFragment : Fragment() {

    private var _binding: FragmentAboutMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMeBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[AboutMeViewModel::class.java]
        val image: ImageView = binding.profileImage
        val profileTextView = binding.aboutText

        viewModel.profileText.observe(viewLifecycleOwner) {
            profileTextView.text = it
        }
        viewModel.image.observe(viewLifecycleOwner) {
            image.setImageResource(it)
        }

        return binding.root
    }
}