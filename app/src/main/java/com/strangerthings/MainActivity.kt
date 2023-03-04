package com.strangerthings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.strangerthings.databinding.ActivityMainBinding

private const val MENU_ID_INFOS = 1

class MainActivity : AppCompatActivity(), View.OnClickListener {


    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    // Lien avec le viewModel
    val model by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Génère la data
        model.loadData()

        // Gestion des erreurs appel d'API
        model.errorMessage.observe(this) {
            if (it.isNotBlank()) {
                binding.tvError.text = it
                binding.tvError.isVisible = true
            }
            else {
                binding.tvError.isVisible = false
            }
        }

        // Affiche la data
        model.data.observe(this, Observer { data ->
            binding.tvNom.setText(data[7].name)
            binding.tvNom2.setText(data[12].name)
            binding.tvNom3.setText(data[14].name)
            binding.tvNom4.setText(data[16].name)
        })

        binding.btCharacters.setOnClickListener(this)
    }

    // Bouton de redirection (nom: Voir Plus)
    override fun onClick(v: View?) {
        if (v === binding.btCharacters) {
            val intent = Intent(this, CharactersActivity::class.java)
            startActivity(intent)
        }
    }

    // Création du Menu en haut à droite
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, MENU_ID_INFOS, 0, "Help")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu)
    }

    // Clic sur le menu et le bouton de redirection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.getItemId() == MENU_ID_INFOS) {
            // Redirection vers InformationsActivity
            val intent = Intent(this, InformationsActivity::class.java)
            startActivity(intent)
        }

        if(item.getItemId() == 2) {
            // Redirection vers CharactersActivity
            val intent = Intent(this, CharactersActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}