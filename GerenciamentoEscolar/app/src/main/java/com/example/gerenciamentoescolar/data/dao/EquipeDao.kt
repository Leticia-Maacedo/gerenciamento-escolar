package gerenciamentoescolar.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import gerenciamentoescolar.data.entities.Equipe

@Dao
interface EquipeDao {
    @Insert
    suspend fun insert(equipe: Equipe)

    @Update
    suspend fun update(equipe: Equipe)

    @Delete
    suspend fun delete(equipe: Equipe)

    @Query("SELECT * FROM equipes ORDER BY nome ASC")
    fun getAll(): LiveData<List<Equipe>>

    @Query("SELECT * FROM equipes WHERE escolaId = :escolaId")
    fun getByEscola(escolaId: Int): LiveData<List<Equipe>>
}
