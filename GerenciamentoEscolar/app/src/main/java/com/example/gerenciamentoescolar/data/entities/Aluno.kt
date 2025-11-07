package gerenciamentoescolar.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "alunos",
    foreignKeys = [ForeignKey(
        entity = Escola::class,
        parentColumns = ["id"],
        childColumns = ["escolaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Aluno(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val idade: Int,
    val escolaId: Int
)
