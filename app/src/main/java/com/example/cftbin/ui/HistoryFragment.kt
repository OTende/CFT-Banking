package com.example.cftbin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cftbin.databinding.FragmentHistoryBinding
import com.example.cftbin.model.UserRepository
import com.example.cftbin.model.room.UserDatabase

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by activityViewModels {
        UserViewModel.Factory(
            UserRepository(
                UserDatabase.getDatabase(requireContext()).userDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val adapter = HistoryAdapter()
        binding.userList.adapter = adapter
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}