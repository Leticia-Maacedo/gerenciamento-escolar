package com.example.gerenciamentoescolar.ui.equipes

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.example.gerenciamentoescolar.data.entities.Equipe
import com.example.gerenciamentoescolar.data.entities.Escola
import com.example.gerenciamentoescolar.databinding.DialogEquipeFormBinding

class EquipeFormDialog(
    context: Context,
    escolas: List<Escola>,
    private val onSubmit: (Equipe) -> Unit,
    private val equipeExistente: Equipe? = null
) {
    private val binding = DialogEquipeFormBinding.inflate(LayoutInflater.from(context))
    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(if (equipeExistente == null) "Nova Equipe" else "Editar Equipe")
        .setView(binding.root)
        .setPositiveButton("Salvar") { _, _ ->
            val nome = binding.inputNome.text.toString()
            val descricao = binding.inputDescricao.text.toString()
            val escolaSelecionada = escolas[binding.spinnerEscolas.selectedItemPosition]

            val equipe = Equipe(
                id = equipeExistente?.id ?: 0,
                nome = nome,
                descricao = descricao,
                escolaId = escolaSelecionada.id
            )
            onSubmit(equipe)
        }
        .setNegativeButton("Cancelar", null)
        .create()

    init {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            escolas.map { it.nome }
        )
        binding.spinnerEscolas.adapter = adapter

        equipeExistente?.let {
            binding.inputNome.setText(it.nome)
            binding.inputDescricao.setText(it.descricao)

            val index = escolas.indexOfFirst { e -> e.id == it.escolaId }
            if (index >= 0) binding.spinnerEscolas.setSelection(index)
        }
    }

    fun show() {
        dialog.show()
    }
}
