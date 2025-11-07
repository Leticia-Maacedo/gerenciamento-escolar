package gerenciamentoescolar.data.repository

import androidx.lifecycle.LiveData
import gerenciamentoescolar.data.dao.AlunoDao
import gerenciamentoescolar.data.entities.Aluno

class AlunoRepository(private val alunoDao: AlunoDao) {

    val todosAlunos: LiveData<List<Aluno>> = alunoDao.getAll()

    fun alunosPorEscola(escolaId: Int): LiveData<List<Aluno>> {
        return alunoDao.getByEscola(escolaId)
    }

    suspend fun inserir(aluno: Aluno) {
        alunoDao.insert(aluno)
    }

    suspend fun atualizar(aluno: Aluno) {
        alunoDao.update(aluno)
    }

    suspend fun deletar(aluno: Aluno) {
        alunoDao.delete(aluno)
    }
}
