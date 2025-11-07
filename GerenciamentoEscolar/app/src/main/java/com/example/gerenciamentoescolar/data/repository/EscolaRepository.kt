package gerenciamentoescolar.data.repository

import androidx.lifecycle.LiveData
import gerenciamentoescolar.data.dao.EscolaDao
import gerenciamentoescolar.data.entities.Escola

class EscolaRepository(private val escolaDao: EscolaDao) {

    val todasEscolas: LiveData<List<Escola>> = escolaDao.getAll()

    suspend fun inserir(escola: Escola) {
        escolaDao.insert(escola)
    }

    suspend fun atualizar(escola: Escola) {
        escolaDao.update(escola)
    }

    suspend fun deletar(escola: Escola) {
        escolaDao.delete(escola)
    }
}
