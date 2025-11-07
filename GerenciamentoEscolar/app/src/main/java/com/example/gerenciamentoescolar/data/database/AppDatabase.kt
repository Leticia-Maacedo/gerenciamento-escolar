package gerenciamentoescolar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gerenciamentoescolar.data.dao.AlunoDao
import gerenciamentoescolar.data.dao.EquipeDao
import gerenciamentoescolar.data.dao.EscolaDao
import gerenciamentoescolar.data.entities.Aluno
import gerenciamentoescolar.data.entities.Equipe
import gerenciamentoescolar.data.entities.Escola

@Database(entities = [Escola::class, Aluno::class, Equipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun escolaDao(): EscolaDao
    abstract fun alunoDao(): AlunoDao
    abstract fun equipeDao(): EquipeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gerenciamento_escolar_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
