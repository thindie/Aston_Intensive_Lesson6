package com.example.thindie.aston_intensive_lesson5.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thindie.aston_intensive_lesson5.FragmentsRouter
import com.example.thindie.aston_intensive_lesson5.R
import com.example.thindie.aston_intensive_lesson5.Router
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentsRouter {
    override val router: Router by lazy {
        Router.inject(
            contactRouter = this,
            id = R.id.activity_fragment_container,
            startDestination = ContactsListFragment()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router.navigate()
    }
}


