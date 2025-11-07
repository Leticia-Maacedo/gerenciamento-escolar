package gerenciamentoescolar.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import gerenciamentoescolar.data.database.AppDatabase
import gerenciamentoescolar.data.entities.Equipe
import gerenciamentoescolar.data.repository.EquipeRepository
import kotlinx.coroutines.launch

class EquipeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EquipeRepository
    val todasEquipes: LiveData<List<Equipe>>

    init {
        val equipeDao = AppDatabase.getDatabase(application).equipeDao()
        repository = EquipeRepository(equipeDao)
        todasEquipes = repository.todasEquipes
    }

    fun equipesPorEscola(escolaId: Int): LiveData<List<Equipe>> {
        return repository.equipesPorEscola(escolaId)
    }

    fun inserir(equipe: Equipe) = viewModelScope.launch {
        repository.inserir(equipe)
    }

    fun atualizar(equipe: Equipe) = viewModelScope.launch {
        repository.atualizar(equipe)
    }

    fun deletar(equipe: Equipe) = viewModelScope.launch {
        repository.deletar(equipe)
    }
}
