package br.com.alura.aluraesporte.ui.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraesporte.R
import br.com.alura.aluraesporte.ui.util.AppWidgetVisibility
import br.com.alura.aluraesporte.ui.viewmodel.AppStateViewModel
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.main_activity_nav_host) }

    private val viewModel: AppStateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initDestinationChangedListener()
    }

    private fun initDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            title = destination.label

            // Está concentrando muita responsabilidade na Activity! Mudar para padrão observer
//            when (destination.id) {
//                R.id.login -> supportActionBar?.hide()
//                else -> supportActionBar?.show()
//            }

            viewModel.widgetsVisibility.observe(this, Observer {
                it?.apply {
                    showAppBarIfNeeded()
                    showBottomNavigationBarIfNeeded()
                }
            })

            main_activity_bottom_navigation_view.setupWithNavController(navController)
        }
    }

    private fun AppWidgetVisibility.showAppBarIfNeeded() {
        if (isAppBarVisible) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }

    private fun AppWidgetVisibility.showBottomNavigationBarIfNeeded() {
        if (isBottomNavigationBarVisible) {
            main_activity_bottom_navigation_view.visibility = VISIBLE
        } else {
            main_activity_bottom_navigation_view.visibility = GONE
        }
    }
}
