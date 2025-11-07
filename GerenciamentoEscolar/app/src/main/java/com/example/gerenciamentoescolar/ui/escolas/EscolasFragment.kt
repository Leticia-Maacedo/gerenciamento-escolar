package com.example.gerenciamentoescolar.ui.escolas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciamentoescolar.data.entities.Escola
import com.example.gerenciamentoescolar.data.viewmodel.EscolaViewModel
import com.example.gerenciamentoescolar.databinding.FragmentEscolasBinding

class EscolasFragment : Fragment() {

    private var _binding: FragmentEscolasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EscolaViewModel by viewModels()
    private lateinit var adapter: EscolasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEscolasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = EscolasAdapter(listOf(),
            onEdit = { escola ->
                EscolaFormDialog(requireContext(), { atualizada ->
                    viewModel.atualizar(atualizada)
                }, escola).show()
            },
            onDelete = { escola ->
                viewModel.deletar(escola)
            })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            EscolaFormDialog(requireContext()) { nova ->
                viewModel.inserir(nova)
            }.show()
        }

        viewModel.todasEscolas.observe(viewLifecycleOwner) { lista ->
            adapter.atualizarLista(lista)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
