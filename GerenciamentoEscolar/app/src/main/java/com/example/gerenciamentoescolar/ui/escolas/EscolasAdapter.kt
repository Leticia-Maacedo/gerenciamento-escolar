package com.example.gerenciamentoescolar.ui.escolas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciamentoescolar.data.entities.Escola
import com.example.gerenciamentoescolar.databinding.ItemEscolaBinding

class EscolasAdapter(
    private var lista: List<Escola>,
    private val onEdit: (Escola) -> Unit,
    private val onDelete: (Escola) -> Unit
) : RecyclerView.Adapter<EscolasAdapter.EscolaViewHolder>() {

    inner class EscolaViewHolder(val binding: ItemEscolaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EscolaViewHolder {
        val binding = ItemEscolaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EscolaViewHolder(binding)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: EscolaViewHolder, position: Int) {
        val escola = lista[position]
        holder.binding.textNome.text = escola.nome
        holder.binding.textEndereco.text = escola.endereco ?: "Sem endereço"

        holder.binding.btnEditar.setOnClickListener { onEdit(escola) }
        holder.binding.btnExcluir.setOnClickListener { onDelete(escola) }
    }

    fun atualizarLista(novaLista: List<Escola>) {
        lista = novaLista
        notifyDataSetChanged()
    }
}
