package com.example.cftbin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cftbin.R
import com.example.cftbin.databinding.FragmentMainBinding
import com.example.cftbin.model.UserRepository
import com.example.cftbin.model.room.UserDatabase

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

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
        _binding = FragmentMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        if (viewModel.isOpen)
            binding.myRoot.setTransition(R.id.button_end, R.id.button_end)
        binding.findBin.setOnClickListener {
            try {
                val text = binding.binEditText.text.toString().toInt()
                viewModel.getUser(text)
                viewModel.user.observe(viewLifecycleOwner) { user ->
                    if (user != null) {
                        viewModel.isOpen = true
                        binding.myRoot.transitionToEnd()
                        binding.binInput.error = null
                        viewModel.saveUser(user.copy(user_id = text))
                        viewModel.updateUsers()
                    } else
                        binding.binInput.error = getString(R.string.no_user)
                }
            } catch (e: java.lang.NumberFormatException) {
                binding.binInput.error = getString(R.string.enter_number)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}