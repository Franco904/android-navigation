package br.com.alura.aluraesporte.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alura.aluraesporte.ui.util.AppWidgetVisibility

class AppStateViewModel : ViewModel() {
    private var _widgetsVisibility = MutableLiveData<AppWidgetVisibility>()
    val widgetsVisibility: LiveData<AppWidgetVisibility>
        get() = _widgetsVisibility

    fun setWidgetsVisibility(widgetVisibility: AppWidgetVisibility) {
        _widgetsVisibility.value = widgetVisibility
    }
}