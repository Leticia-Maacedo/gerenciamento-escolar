package com.example.gerenciamentoescolar.ui.equipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciamentoescolar.data.entities.Equipe
import com.example.gerenciamentoescolar.data.viewmodel.EquipeViewModel
import com.example.gerenciamentoescolar.data.viewmodel.EscolaViewModel
import com.example.gerenciamentoescolar.databinding.FragmentEquipesBinding
import kotlinx.coroutines.launch

class EquipesFragment : Fragment() {

    private var _binding: FragmentEquipesBinding? = null
    private val binding get() = _binding!!
    private val viewModelEquipe: EquipeViewModel by viewModels()
    private val viewModelEscola: EscolaViewModel by viewModels()
    private lateinit var adapter: EquipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = EquipesAdapter(listOf(),
            onEdit = { equipe ->
                lifecycleScope.launch {
                    val escolas = viewModelEscola.todasEscolas.value ?: listOf()
                    EquipeFormDialog(requireContext(), escolas, { atualizada ->
                        viewModelEquipe.atualizar(atualizada)
                    }, equipe).show()
                }
            },
            onDelete = { equipe ->
                viewModelEquipe.deletar(equipe)
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            lifecycleScope.launch {
                val escolas = viewModelEscola.todasEscolas.value ?: listOf()
                EquipeFormDialog(requireContext(), escolas) { nova ->
                    viewModelEquipe.inserir(nova)
                }.show()
            }
        }

        viewModelEquipe.todasEquipes.observe(viewLifecycleOwner) { lista ->
            adapter.atualizarLista(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
