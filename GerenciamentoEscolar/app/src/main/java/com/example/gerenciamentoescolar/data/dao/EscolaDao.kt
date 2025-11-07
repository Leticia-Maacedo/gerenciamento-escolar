package com.example.gerenciamentoescolar.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import gerenciamentoescolar.data.entities.Escola

@Dao
interface EscolaDao {
    @Insert
    suspend fun insert(escola: Escola)

    @Update
    suspend fun update(escola: Escola)

    @Delete
    suspend fun delete(escola: Escola)

    @Query("SELECT * FROM escolas ORDER BY nome ASC")
    fun getAll(): LiveData<List<Escola>>
}
