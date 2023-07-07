package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ListaPagamentosAdapter
import br.com.alura.aluraesporte.ui.util.AppWidgetVisibility
import br.com.alura.aluraesporte.ui.viewmodel.AppStateViewModel
import br.com.alura.aluraesporte.ui.viewmodel.ListaPagamentosViewModel
import kotlinx.android.synthetic.main.lista_pagamentos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaPagamentosFragment : Fragment() {
    private val adapter: ListaPagamentosAdapter by inject()

    private val appViewModel: AppStateViewModel by sharedViewModel()
    private val viewModel: ListaPagamentosViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_pagamentos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appViewModel.setWidgetsVisibility(
            AppWidgetVisibility(
                isAppBarVisible = true,
                isBottomNavigationBarVisible = true
            )
        )

        lista_pagamentos_recyclerview.adapter = adapter

        viewModel.findAll().observe(this, Observer {
            it?.let { pagamentos -> adapter.add(pagamentos) }
        })
    }
}