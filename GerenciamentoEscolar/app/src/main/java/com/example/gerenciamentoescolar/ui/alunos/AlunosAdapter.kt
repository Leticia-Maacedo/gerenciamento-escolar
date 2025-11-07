package com.example.gerenciamentoescolar.ui.alunos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciamentoescolar.data.entities.Aluno
import com.example.gerenciamentoescolar.databinding.ItemAlunoBinding

class AlunosAdapter(
    private var lista: List<Aluno>,
    private val onEdit: (Aluno) -> Unit,
    private val onDelete: (Aluno) -> Unit
) : RecyclerView.Adapter<AlunosAdapter.AlunoViewHolder>() {

    inner class AlunoViewHolder(val binding: ItemAlunoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val binding = ItemAlunoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlunoViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        val aluno = lista[position]
        holder.binding.textNome.text = aluno.nome
        holder.binding.textIdade.text = "Idade: ${aluno.idade}"
        holder.binding.textEscola.text = "Escola ID: ${aluno.escolaId}"

        holder.binding.btnEditar.setOnClickListener { onEdit(aluno) }
        holder.binding.btnExcluir.setOnClickListener { onDelete(aluno) }
    }

    fun atualizarLista(novaLista: List<Aluno>) {
        lista = novaLista
        notifyDataSetChanged()
    }
}
