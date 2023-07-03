package br.com.alura.aluraesporte.ui.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraesporte.NavGraphDirections
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseFragment: Fragment() {
    private val loginViewModel: LoginViewModel by viewModel()
    val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!loginViewModel.isLogged()) {
            navigateToLogin()
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.lista_produtos_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.lista_produtos_menu_logout) {
            navigateToLogin()
        }

        return super.onOptionsItemSelected(item)
    }

    fun setLoggedOut() {

    }

    fun navigate(directions: NavDirections) {
        navController.navigate(directions)
    }

    private fun navigateToLogin() {
        navController.navigate(NavGraphDirections.actionGlobalLogin())
    }
}