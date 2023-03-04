package com.strangerthings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.strangerthings.databinding.ActivityCharactersBinding

class CharactersActivity : AppCompatActivity() {

    val binding by lazy { ActivityCharactersBinding.inflate(layoutInflater) }
    // Lien vers viewModel
    val model by lazy { ViewModelProvider(this).get(CharactersViewModel::class.java) }

    // RecyclerView
    val adapter = CharactersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Gestion des erreurs appel d'API
        model.errorMessage.observe(this) {
            if (it.isNotBlank()) {
                binding.tvError2.text = it
                binding.tvError2.isVisible = true
            }
            else {
                binding.tvError2.isVisible = false
            }
        }

        // Réglage recyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Génère la data (MAJ)
        model.loadData()
        model.list.observe(this, Observer { data ->
            adapter.submitList(data.toList())
        })
    }
}