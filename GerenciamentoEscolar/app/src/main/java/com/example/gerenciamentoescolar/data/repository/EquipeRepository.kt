package gerenciamentoescolar.data.repository

import androidx.lifecycle.LiveData
import gerenciamentoescolar.data.dao.EquipeDao
import gerenciamentoescolar.data.entities.Equipe

class EquipeRepository(private val equipeDao: EquipeDao) {

    val todasEquipes: LiveData<List<Equipe>> = equipeDao.getAll()

    fun equipesPorEscola(escolaId: Int): LiveData<List<Equipe>> {
        return equipeDao.getByEscola(escolaId)
    }

    suspend fun inserir(equipe: Equipe) {
        equipeDao.insert(equipe)
    }

    suspend fun atualizar(equipe: Equipe) {
        equipeDao.update(equipe)
    }

    suspend fun deletar(equipe: Equipe) {
        equipeDao.delete(equipe)
    }
}
