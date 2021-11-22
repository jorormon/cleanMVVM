package com.jordiortuno.cleanmvvm.framework.ui.main.library

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.jordiortuno.cleanmvvm.databinding.LibraryFragmentBinding
import com.jordiortuno.cleanmvvm.domain.Document
import com.jordiortuno.cleanmvvm.framework.ui.main.HomeViewModel
import com.jordiortuno.cleanmvvm.framework.utils.IntentUtil.createOpenIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment:Fragment() {
    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel:LibraryViewModel by viewModels()
    companion object {
        const val READ_REQUEST_CODE = 100

        fun newInstance() = LibraryFragment()
    }


    private lateinit var adapter: DocumentsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            //mainActivityDelegate = context as MainActivityDelegate
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val adapter = DocumentsAdapter() {
       //    mainActivityDelegate.openDocument(it)
       }

        binding.documentsRecyclerView.adapter = adapter


        viewModel.documents.observe(viewLifecycleOwner, Observer { adapter.update(it) })
        viewModel.loadDocuments()

        binding.fab.setOnClickListener { startActivityForResult(createOpenIntent(), READ_REQUEST_CODE) }
    }
    private fun updateDocuments(list: List<Document>){
        adapter.update(list)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Process open file intent.
       if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
          data?.data?.also { uri -> viewModel.addDocument(uri) }
       } else {
          super.onActivityResult(requestCode, resultCode, data)
      }
    }
}
