package com.example.gerenciamentoescolar.ui.alunos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.example.gerenciamentoescolar.data.entities.Aluno
import com.example.gerenciamentoescolar.data.entities.Escola
import com.example.gerenciamentoescolar.databinding.DialogAlunoFormBinding

class AlunoFormDialog(
    context: Context,
    escolas: List<Escola>,
    private val onSubmit: (Aluno) -> Unit,
    private val alunoExistente: Aluno? = null
) {
    private val binding = DialogAlunoFormBinding.inflate(LayoutInflater.from(context))
    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(if (alunoExistente == null) "Novo Aluno" else "Editar Aluno")
        .setView(binding.root)
        .setPositiveButton("Salvar") { _, _ ->
            val nome = binding.inputNome.text.toString()
            val idade = binding.inputIdade.text.toString().toIntOrNull() ?: 0
            val escolaSelecionada = escolas[binding.spinnerEscolas.selectedItemPosition]

            val aluno = Aluno(
                id = alunoExistente?.id ?: 0,
                nome = nome,
                idade = idade,
                escolaId = escolaSelecionada.id
            )
            onSubmit(aluno)
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

        alunoExistente?.let {
            binding.inputNome.setText(it.nome)
            binding.inputIdade.setText(it.idade.toString())

            val index = escolas.indexOfFirst { e -> e.id == it.escolaId }
            if (index >= 0) binding.spinnerEscolas.setSelection(index)
        }
    }

    fun show() {
        dialog.show()
    }
}
