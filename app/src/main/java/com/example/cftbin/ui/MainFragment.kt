package com.example.cftbin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.cftbin.R
import com.example.cftbin.databinding.FragmentMainBinding
import com.example.cftbin.model.UserRepository

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: UserViewModel by viewModels {
        UserViewModel.Factory(UserRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        viewModel.user.observe(viewLifecycleOwner) {
            binding.findBin.text = it.brand
        }
        binding.findBin.setOnClickListener {
            val text = binding.binEditText.text.toString().toInt()
            viewModel.getUser(text)
            binding.root.transitionToEnd()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}