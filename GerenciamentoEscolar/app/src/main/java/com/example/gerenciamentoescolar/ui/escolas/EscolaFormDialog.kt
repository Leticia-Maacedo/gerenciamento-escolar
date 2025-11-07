package com.example.gerenciamentoescolar.ui.escolas

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.gerenciamentoescolar.data.entities.Escola
import com.example.gerenciamentoescolar.databinding.DialogEscolaFormBinding

class EscolaFormDialog(
    context: Context,
    private val onSubmit: (Escola) -> Unit,
    private val escolaExistente: Escola? = null
) {
    private val binding = DialogEscolaFormBinding.inflate(LayoutInflater.from(context))
    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(if (escolaExistente == null) "Nova Escola" else "Editar Escola")
        .setView(binding.root)
        .setPositiveButton("Salvar") { _, _ ->
            val nome = binding.inputNome.text.toString()
            val endereco = binding.inputEndereco.text.toString()
            val escola = Escola(
                id = escolaExistente?.id ?: 0,
                nome = nome,
                endereco = endereco
            )
            onSubmit(escola)
        }
        .setNegativeButton("Cancelar", null)
        .create()

    fun show() {
        escolaExistente?.let {
            binding.inputNome.setText(it.nome)
            binding.inputEndereco.setText(it.endereco)
        }
        dialog.show()
    }
}


