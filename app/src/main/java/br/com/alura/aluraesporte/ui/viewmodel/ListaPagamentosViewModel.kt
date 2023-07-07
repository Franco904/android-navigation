package br.com.alura.aluraesporte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.aluraesporte.model.Pagamento
import br.com.alura.aluraesporte.repository.PagamentoRepository
import br.com.alura.aluraesporte.repository.ProdutoRepository

class ListaPagamentosViewModel(
    private val pagamentoRepository: PagamentoRepository,
    private val produtoRepository: ProdutoRepository
): ViewModel() {
    fun save(pagamento: Pagamento) {
        pagamentoRepository.salva(pagamento)
    }

    fun findProdutoById(id: Long) {
        produtoRepository.buscaPorId(id)
    }

    fun findAll(): LiveData<List<Pagamento>> = pagamentoRepository.buscaTodos()
}
