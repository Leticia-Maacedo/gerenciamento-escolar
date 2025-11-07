package com.example.gerenciamentoescolar.ui.alunos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciamentoescolar.data.entities.Aluno
import com.example.gerenciamentoescolar.data.viewmodel.AlunoViewModel
import com.example.gerenciamentoescolar.data.viewmodel.EscolaViewModel
import com.example.gerenciamentoescolar.databinding.FragmentAlunosBinding
import kotlinx.coroutines.launch

class AlunosFragment : Fragment() {

    private var _binding: FragmentAlunosBinding? = null
    private val binding get() = _binding!!
    private val viewModelAluno: AlunoViewModel by viewModels()
    private val viewModelEscola: EscolaViewModel by viewModels()
    private lateinit var adapter: AlunosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlunosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = AlunosAdapter(listOf(),
            onEdit = { aluno ->
                lifecycleScope.launch {
                    val escolas = viewModelEscola.todasEscolas.value ?: listOf()
                    AlunoFormDialog(requireContext(), escolas, { atualizado ->
                        viewModelAluno.atualizar(atualizado)
                    }, aluno).show()
                }
            },
            onDelete = { aluno ->
                viewModelAluno.deletar(aluno)
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            lifecycleScope.launch {
                val escolas = viewModelEscola.todasEscolas.value ?: listOf()
                AlunoFormDialog(requireContext(), escolas) { novo ->
                    viewModelAluno.inserir(novo)
                }.show()
            }
        }

        viewModelAluno.todosAlunos.observe(viewLifecycleOwner) { lista ->
            adapter.atualizarLista(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
