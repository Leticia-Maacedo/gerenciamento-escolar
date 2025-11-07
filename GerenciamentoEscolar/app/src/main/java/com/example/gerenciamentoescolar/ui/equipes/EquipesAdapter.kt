package com.example.gerenciamentoescolar.ui.equipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciamentoescolar.data.entities.Equipe
import com.example.gerenciamentoescolar.databinding.ItemEquipeBinding

class EquipesAdapter(
    private var lista: List<Equipe>,
    private val onEdit: (Equipe) -> Unit,
    private val onDelete: (Equipe) -> Unit
) : RecyclerView.Adapter<EquipesAdapter.EquipeViewHolder>() {

    inner class EquipeViewHolder(val binding: ItemEquipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipeViewHolder {
        val binding = ItemEquipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EquipeViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: EquipeViewHolder, position: Int) {
        val equipe = lista[position]
        holder.binding.textNome.text = equipe.nome
        holder.binding.textDescricao.text = equipe.descricao
        holder.binding.textEscola.text = "Escola ID: ${equipe.escolaId}"

        holder.binding.btnEditar.setOnClickListener { onEdit(equipe) }
        holder.binding.btnExcluir.setOnClickListener { onDelete(equipe) }
    }

    fun atualizarLista(novaLista: List<Equipe>) {
        lista = novaLista
        notifyDataSetChanged()
    }
}
