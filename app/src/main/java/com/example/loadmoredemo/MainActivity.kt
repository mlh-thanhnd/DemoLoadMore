package com.example.loadmoredemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loadmoredemo.models.InformationPokemon
import com.example.loadmoredemo.viewwmodels.InformationPokemonViewModel
import com.example.loadmoredemo.viewwmodels.ListPokemonViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val OFFSET = 20
    }

    private var mListPokemonViewModel: ListPokemonViewModel? = null
    private var mInformationPokemonViewModel: InformationPokemonViewModel? = null
    private var mListPokemon: ArrayList<InformationPokemon> = ArrayList()
    var mListPokemonAdapter: ListPokemonAdapter? = null
    var mCountApi = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerViewPokemon()
        initObserver()
        getListPokemon()
    }

    private fun initObserver() {
        mListPokemonViewModel = ListPokemonViewModel()
        mInformationPokemonViewModel = InformationPokemonViewModel()
        mListPokemonViewModel?.mListPokemon?.observe(this, { list ->
            list?.results?.forEach { name ->
                mCountApi++
                mInformationPokemonViewModel?.getAPokemon(name.name ?: "")
            }
        })

        mInformationPokemonViewModel?.mAPokemon?.observe(this, {
            mCountApi--
            val pokemonModel = InformationPokemon(name = it?.name)
            mListPokemon.add(pokemonModel)
            setListToRv()
        })
    }

    private fun setListToRv() {
        if (mCountApi == 0) mListPokemonAdapter?.setList(mListPokemon)
    }

    private fun initRecyclerViewPokemon() {
        mListPokemonAdapter = ListPokemonAdapter()
        rvPokemon.layoutManager = LinearLayoutManager(this)
        rvPokemon.adapter = mListPokemonAdapter
        rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && mListPokemonAdapter?.mIsLoadMore == false) {
                    getListPokemon()
                    mListPokemonAdapter?.setLoadMoreItem(isLoadMore = true)
                }
            }
        })
    }

    fun getListPokemon() = mListPokemonViewModel?.getListPokemon(mListPokemon.size, OFFSET)

}