package br.com.alura.aluraesporte.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.aluraesporte.repository.LoginRepository

class LoginViewModel(
    private val repository: LoginRepository
): ViewModel() {
    fun setLoggedIn() {
        repository.setLoggedIn()
    }

    fun setLoggedOut() {
        repository.setLoggedOut()
    }

    fun isLogged(): Boolean = repository.isLogged()
}