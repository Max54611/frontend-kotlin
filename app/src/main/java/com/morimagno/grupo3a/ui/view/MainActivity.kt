package com.morimagno.grupo3a.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.morimagno.grupo3a.R
import com.morimagno.grupo3a.data.adapter.ProductAdapter
import com.morimagno.grupo3a.data.model.ProductResponse
import com.morimagno.grupo3a.databinding.ActivityMainBinding
import com.morimagno.grupo3a.ui.viewmodel.MainViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){
    private val viewModel by lazy {MainViewModel()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView:BottomNavigationView=binding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}
