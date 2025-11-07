package gerenciamentoescolar.data.viewmodel

import android.app.Application
import androidx.lifecycle.*
import gerenciamentoescolar.data.database.AppDatabase
import gerenciamentoescolar.data.entities.Aluno
import gerenciamentoescolar.data.repository.AlunoRepository
import kotlinx.coroutines.launch

class AlunoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlunoRepository
    val todosAlunos: LiveData<List<Aluno>>

    init {
        val alunoDao = AppDatabase.getDatabase(application).alunoDao()
        repository = AlunoRepository(alunoDao)
        todosAlunos = repository.todosAlunos
    }

    fun alunosPorEscola(escolaId: Int): LiveData<List<Aluno>> {
        return repository.alunosPorEscola(escolaId)
    }

    fun inserir(aluno: Aluno) = viewModelScope.launch {
        repository.inserir(aluno)
    }

    fun atualizar(aluno: Aluno) = viewModelScope.launch {
        repository.atualizar(aluno)
    }

    fun deletar(aluno: Aluno) = viewModelScope.launch {
        repository.deletar(aluno)
    }
}
