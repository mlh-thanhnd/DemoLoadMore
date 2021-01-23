package com.example.loadmoredemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoredemo.models.InformationPokemon
import kotlinx.android.synthetic.main.item_list_pokemon.view.*
import kotlinx.android.synthetic.main.item_progressbar.view.*

class ListPokemonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListPokemon: ArrayList<InformationPokemon> = ArrayList()
    var mIsLoadMore = false

    companion object {
        const val TYPE_NORMAL = 0
        const val TYPE_LOAD_MORE = 1
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setList(list: ArrayList<InformationPokemon>) {
        this.mListPokemon = list
        setLoadMoreItem(isLoadMore = false)
        notifyDataSetChanged()
    }

    fun setLoadMoreItem(isLoadMore: Boolean) {
        mIsLoadMore = isLoadMore
        if (isLoadMore) {
            mListPokemon.add(InformationPokemon(itemType = TYPE_LOAD_MORE))
        } else {
            mListPokemon.removeAll { it.itemType == TYPE_LOAD_MORE }
        }
        notifyItemChanged(mListPokemon.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    if (viewType == TYPE_NORMAL) R.layout.item_list_pokemon else R.layout.item_progressbar,
                    parent,
                    false
                )
        )
    }


    override fun getItemCount(): Int {
        return this.mListPokemon.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            holder.itemView.tvNamePokemon.text = mListPokemon[position].name?.toUpperCase()
        } else {
            holder.itemView.ProgressBar.visibility = View.VISIBLE
        }
    }

    override fun getItemViewType(position: Int) = mListPokemon[position].itemType

}