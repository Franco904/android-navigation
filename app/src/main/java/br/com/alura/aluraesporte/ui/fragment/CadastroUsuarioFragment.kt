package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.util.AppWidgetVisibility
import br.com.alura.aluraesporte.ui.viewmodel.AppStateViewModel
import kotlinx.android.synthetic.main.cadastro_usuario.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CadastroUsuarioFragment : Fragment() {
    private val navController by lazy { findNavController() }

    private val appViewModel: AppStateViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cadastro_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appViewModel.setWidgetsVisibility(
            AppWidgetVisibility(
                isAppBarVisible = false,
                isBottomNavigationBarVisible = false
            )
        )

        cadastro_usuario_botao_cadastrar.setOnClickListener {
            navController.popBackStack()
        }
    }
}