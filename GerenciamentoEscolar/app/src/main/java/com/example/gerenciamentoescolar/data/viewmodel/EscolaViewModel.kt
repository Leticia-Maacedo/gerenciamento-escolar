package gerenciamentoescolar.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import gerenciamentoescolar.data.database.AppDatabase
import gerenciamentoescolar.data.entities.Escola
import gerenciamentoescolar.data.repository.EscolaRepository
import kotlinx.coroutines.launch

class EscolaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EscolaRepository
    val todasEscolas: LiveData<List<Escola>>

    init {
        val escolaDao = AppDatabase.getDatabase(application).escolaDao()
        repository = EscolaRepository(escolaDao)
        todasEscolas = repository.todasEscolas
    }

    fun inserir(escola: Escola) = viewModelScope.launch {
        repository.inserir(escola)
    }

    fun atualizar(escola: Escola) = viewModelScope.launch {
        repository.atualizar(escola)
    }

    fun deletar(escola: Escola) = viewModelScope.launch {
        repository.deletar(escola)
    }
}
