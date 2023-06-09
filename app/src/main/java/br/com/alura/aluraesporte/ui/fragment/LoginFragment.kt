package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.util.AppWidgetVisibility
import br.com.alura.aluraesporte.ui.viewmodel.AppStateViewModel
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val navController by lazy { findNavController() }

    private val appViewModel: AppStateViewModel by sharedViewModel()
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appViewModel.setWidgetsVisibility(
            AppWidgetVisibility(
                isAppBarVisible = false,
                isBottomNavigationBarVisible = false
            )
        )

        login_botao_logar.setOnClickListener {
            viewModel.setLoggedIn()
            navigateToListaProdutos()
        }

        login_botao_cadastrar.setOnClickListener {
            navigateToCadastroUsuario()
        }
    }

    private fun navigateToListaProdutos() {
        val directions = LoginFragmentDirections.actionLoginToListaProdutos()
        navController.navigate(directions)
    }

    private fun navigateToCadastroUsuario() {
        val directions = LoginFragmentDirections.actionLoginToCadastroUsuario()
        navController.navigate(directions)
    }
}