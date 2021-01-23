package com.example.loadmoredemo.viewwmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.api.RetrofitClient
import com.example.pokemonapp.models.ListPokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPokemonViewModel : ViewModel() {
    var mListPokemon: MutableLiveData<ListPokemon?> = MutableLiveData()
    fun getListPokemon(offset: Int, limit: Int) {
        RetrofitClient.instance.getListPokemon(offset, limit)
            .enqueue(object : Callback<ListPokemon> {
                override fun onFailure(call: Call<ListPokemon>, t: Throwable) {
                    // tu xu ly =))))
                }

                override fun onResponse(call: Call<ListPokemon>, response: Response<ListPokemon>) {
                    if (response.isSuccessful) {
                        mListPokemon.value = response.body()
                    } else {
                        mListPokemon.value = null
                    }
                }
            }
            )
    }
}