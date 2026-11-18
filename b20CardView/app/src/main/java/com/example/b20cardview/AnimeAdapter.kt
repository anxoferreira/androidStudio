package com.example.b20cardview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimeAdapter(private val items: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_card, parent, false)
        return AnimeViewHolder(v)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = items[position]
        holder.imagen.setImageResource(anime.imagen)
        holder.nombre.text = anime.nombre
        holder.visitas.text = "Visitas: ${anime.visitas}"
    }

    class AnimeViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val imagen: ImageView = v.findViewById(R.id.imagen)
        val nombre: TextView = v.findViewById(R.id.nombre)
        val visitas: TextView = v.findViewById(R.id.visitas)
    }
}
