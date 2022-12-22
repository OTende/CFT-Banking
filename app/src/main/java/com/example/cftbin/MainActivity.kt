package com.example.cftbin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.cftbin.model.User
import com.example.cftbin.model.UserRepository
import com.example.cftbin.ui.UserViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = UserViewModel(UserRepository())
        viewModel.getUser(46717121)
        viewModel.user.observe(this) {
//            findViewById<TextView>(R.id.text_view).text = it.brand
        }
        setContentView(R.layout.activity_main)
    }
}