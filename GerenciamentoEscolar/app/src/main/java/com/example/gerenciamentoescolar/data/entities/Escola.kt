package gerenciamentoescolar.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "escolas")
data class Escola(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val endereco: String
)
