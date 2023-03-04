package com.strangerthings

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.strangerthings.R
import com.strangerthings.databinding.RowCharactersBinding
import com.strangerthings.databinding.ActivityCharactersBinding
import com.squareup.picasso.Picasso


class CharactersListAdapter : ListAdapter<CharactersBean, CharactersListAdapter.ViewHolder>(
    Comparator()
) {

    class ViewHolder(val binding : RowCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<CharactersBean>() {
        override fun areItemsTheSame(oldItem: CharactersBean, newItem: CharactersBean) = oldItem === newItem

        override fun areContentsTheSame(oldItem: CharactersBean, newItem: CharactersBean)= oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(RowCharactersBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        // Génèrent les données et tester les données
        if (currentItem.name.isNullOrEmpty()) {
            holder.binding.tvName.text = "Pas renseigné"
        } else {
            holder.binding.tvName.text = currentItem.name
        }
        if (currentItem.born.isNullOrEmpty()) {
            holder.binding.tvNaissance.text = "-"
        } else {
            holder.binding.tvNaissance.text = currentItem.born
        }

        if (currentItem.residence.isNullOrEmpty() || currentItem.residence?.get(0) == "unknown") {
            holder.binding.tvResidence.text = "Inconnu"
        } else {
            holder.binding.tvResidence.text = currentItem.residence?.get(0)
        }

        if (currentItem.photo.isNullOrEmpty()) {
            holder.binding.imageView.setImageResource(R.drawable.baseline_person_24)
        } else {
            Picasso.get().load(currentItem.photo).into(holder.binding.imageView)
        }

        holder.binding.main.setOnClickListener {
            Toast.makeText(holder.binding.imageView.context, currentItem.name, Toast.LENGTH_LONG).show()
        }
    }
}
/*
class CharactersListAdapter : ListAdapter<CharactersBean, RecyclerView.ViewHolder>(Comparator()) {

    class CharacterViewHolder(val binding : RowCharactersBinding) : RecyclerView.ViewHolder(binding.root)
    class ActivityViewHolder(val binding : ActivityCharactersBinding) : RecyclerView.ViewHolder(binding.root)

    var isMaleSelected = false

    class Comparator : DiffUtil.ItemCallback<CharactersBean>() {
        override fun areItemsTheSame(oldItem: CharactersBean, newItem: CharactersBean) = oldItem === newItem
        override fun areContentsTheSame(oldItem: CharactersBean, newItem: CharactersBean)= oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int {
        // Return the view type based on the position, for example:
        // if (position == 0) return R.layout.activity_characters
        // else return R.layout.row_characters
        return R.layout.row_characters
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.activity_characters -> {
                val binding = ActivityCharactersBinding.inflate(layoutInflater, parent, false)
                ActivityViewHolder(binding)
            }
            else -> {
                val binding = RowCharactersBinding.inflate(layoutInflater, parent, false)
                CharacterViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when(holder) {
            is ActivityViewHolder -> {
                // bind data to activity view holder
                // Vérifier si l'utilisateur a sélectionné l'option "characters masculins" et si currentItem est masculin
                if (isMaleSelected && currentItem.gender == "male") {
                    // Afficher le currentItem seulement s'il est masculin et l'option "characters masculins" est sélectionnée
                    ActivityCharactersBinding.tvName.text = currentItem.name
                    holder.binding.tvNaissance.text = currentItem.born
                    if (currentItem.photo.isNullOrEmpty()) {
                        holder.binding.imageView.setImageResource(R.drawable.baseline_person_24)
                    } else {
                        Picasso.get().load(currentItem.photo).into(holder.binding.imageView)
                    }
                }
            }
            is CharacterViewHolder -> {
                // bind data to character view holder
                holder.binding.tvName.text = currentItem.name
                holder.binding.tvNaissance.text = currentItem.born
                if (currentItem.photo.isNullOrEmpty()) {
                    holder.binding.imageView.setImageResource(R.drawable.baseline_person_24)
                } else {
                    Picasso.get().load(currentItem.photo).into(holder.binding.imageView)
                }

                holder.binding.main.setOnClickListener {
                    Toast.makeText(holder.binding.imageView.context, currentItem.name, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
*/