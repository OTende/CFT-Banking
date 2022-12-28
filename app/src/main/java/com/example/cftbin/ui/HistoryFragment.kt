package com.example.cftbin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.cftbin.R
import com.example.cftbin.model.UserRepository
import com.example.cftbin.model.room.UserDatabase
import kotlinx.coroutines.runBlocking

class HistoryFragment : Fragment() {
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
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel.getUsers()
        view.findViewById<TextView>(R.id.asdf).text = viewModel.users.map { it.country }.toString()
//        runBlocking {
//            view.findViewById<TextView>(R.id.asdf).text = UserDatabase.getDatabase(requireContext()).userDao().getAll().toString() //viewModel.users.toString()
//        }
        return view
    }
}