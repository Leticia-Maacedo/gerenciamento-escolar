package gerenciamentoescolar.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "equipes",
    foreignKeys = [ForeignKey(
        entity = Escola::class,
        parentColumns = ["id"],
        childColumns = ["escolaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Equipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val descricao: String,
    val escolaId: Int
)
