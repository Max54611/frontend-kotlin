package com.morimagno.grupo3a.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.morimagno.grupo3a.data.adapter.ProductAdapter
import com.morimagno.grupo3a.data.model.ProductResponse
import com.morimagno.grupo3a.databinding.FragmentHomeBinding
import com.morimagno.grupo3a.ui.viewmodel.HomeViewModel
import com.morimagno.grupo3a.ui.viewmodel.MainViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.RewindAnimationSetting
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    CardStackListener,
    ProductAdapter.Callback{

    private var listProduct:List<ProductResponse> = emptyList()

    private val homeViewModel: HomeViewModel by viewModels()

    private val manager by lazy { CardStackLayoutManager(context,this) }

    private val adapter by lazy{ ProductAdapter(listProduct, this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeCard()
        observeValues()
    }

    private fun observeValues(){
        binding.floatingActionButton.setOnClickListener {
            //Rewind
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            binding.rvProduct.rewind()
        }
        binding.floatingActionButton2.setOnClickListener {
            //Skip
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()

            manager.setSwipeAnimationSetting(setting)
            binding.rvProduct.swipe()
        }

        binding.floatingActionButton3.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvProduct.swipe()
        }

        homeViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        homeViewModel.productList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()

            binding.floatingActionButton.visibility = View.VISIBLE
            binding.floatingActionButton2.visibility = View.VISIBLE
            binding.floatingActionButton3.visibility = View.VISIBLE
        }


    }


    private fun initializeCard(){
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvProduct.layoutManager = manager

        binding.rvProduct.adapter = adapter

        binding.rvProduct.itemAnimator.apply{
            if(this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    override fun onClickProductInformation(productResponse: ProductResponse){

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        if(direction == Direction.Right){
            val product = adapter.list[manager.topPosition - 1]
            homeViewModel.saveProduct(product,requireContext())
        }
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

}