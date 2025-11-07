package gerenciamentoescolar.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import gerenciamentoescolar.data.entities.Aluno

@Dao
interface AlunoDao {
    @Insert
    suspend fun insert(aluno: Aluno)

    @Update
    suspend fun update(aluno: Aluno)

    @Delete
    suspend fun delete(aluno: Aluno)

    @Query("SELECT * FROM alunos ORDER BY nome ASC")
    fun getAll(): LiveData<List<Aluno>>

    @Query("SELECT * FROM alunos WHERE escolaId = :escolaId")
    fun getByEscola(escolaId: Int): LiveData<List<Aluno>>
}
