package br.com.alura.aluraesporte.di

import android.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.alura.aluraesporte.database.AppDatabase
import br.com.alura.aluraesporte.database.dao.ProdutoDAO
import br.com.alura.aluraesporte.model.Produto
import br.com.alura.aluraesporte.repository.LoginRepository
import br.com.alura.aluraesporte.repository.PagamentoRepository
import br.com.alura.aluraesporte.repository.ProdutoRepository
import br.com.alura.aluraesporte.ui.fragment.DetalhesProdutoFragment
import br.com.alura.aluraesporte.ui.fragment.ListaProdutosFragment
import br.com.alura.aluraesporte.ui.fragment.PagamentoFragment
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ListaPagamentosAdapter
import br.com.alura.aluraesporte.ui.recyclerview.adapter.ProdutosAdapter
import br.com.alura.aluraesporte.ui.viewmodel.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.math.BigDecimal

private const val NOME_BANCO_DE_DADOS = "aluraesporte.db"
private const val NOME_BANCO_DE_DADOS_TESTE = "aluraesporte-test.db"

val testeDatabaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS_TESTE
        ).fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(IO).launch {
                        val dao: ProdutoDAO by inject()
                        dao.salva(
                            Produto(
                                nome = "Bola de futebol",
                                preco = BigDecimal("100")
                            ), Produto(
                                nome = "Camisa",
                                preco = BigDecimal("80")
                            ),
                            Produto(
                                nome = "Chuteira",
                                preco = BigDecimal("120")
                            ), Produto(
                                nome = "Bermuda",
                                preco = BigDecimal("60")
                            )
                        )
                    }
                }
            }).build()
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            NOME_BANCO_DE_DADOS
        ).build()
    }
}

val daoModule = module {
    single { get<AppDatabase>().produtoDao() }
    single { get<AppDatabase>().pagamentoDao() }
    single { ProdutoRepository(get()) }
    single { PagamentoRepository(get()) }
    single { LoginRepository(get()) }
    single { PreferenceManager.getDefaultSharedPreferences(get())}
}

val uiModule = module {
    factory { DetalhesProdutoFragment() }
    factory { ListaProdutosFragment() }
    factory { PagamentoFragment() }
    factory { ProdutosAdapter(get()) }
    factory { ListaPagamentosAdapter(get()) }
}

val viewModelModule = module {
    viewModel { AppStateViewModel() }
    viewModel { ProdutosViewModel(get()) }
    viewModel { (id: Long) -> DetalhesProdutoViewModel(id, get()) }
    viewModel { PagamentoViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ListaPagamentosViewModel(get(), get()) }
}