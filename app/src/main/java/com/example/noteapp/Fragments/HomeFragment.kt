package com.example.noteapp.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.Adapter.NoteAdapter
import com.example.noteapp.MainActivity
import com.example.noteapp.Model.Note
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NoteViewModel
import com.example.noteapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel : NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel

        setUpRecyclerView()

        binding.btnAddNote.setOnClickListener { mView ->

            mView.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }
    }

    private fun setUpRecyclerView(){

        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )

            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.getAllNote().observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            })
        }
    }
    private fun updateUI(note : List<Note>){
        if (note.isNotEmpty()){
            binding.recyclerView.visibility = View.VISIBLE
            binding.tvNoNote.visibility = View.GONE
        }else{
            binding.recyclerView.visibility = View.GONE
            binding.tvNoNote.visibility = View.VISIBLE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}