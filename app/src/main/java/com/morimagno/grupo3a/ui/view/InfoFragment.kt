package com.morimagno.grupo3a.ui.view

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.fragment.app.viewModels
import com.morimagno.grupo3a.databinding.FragmentInfoBinding
import com.morimagno.grupo3a.ui.viewmodel.InfoViewModel

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://dialprix.es/blog/10-beneficios-y-propiedades-de-consumir-frutos-secos-a-diario/"

        webView = binding.wvInfoFragment

        webView.settings.javaScriptEnabled = true

        webView.loadUrl(url)
    }
}