package com.example.loadmoredemo.viewwmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loadmoredemo.models.InformationPokemon
import com.example.pokemonapp.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformationPokemonViewModel : ViewModel() {
    var mAPokemon: MutableLiveData<InformationPokemon?> = MutableLiveData()

    fun getAPokemon(namePokemon: String) {
        RetrofitClient.instance.getInformationAPokemon(namePokemon)
            .enqueue(object : Callback<InformationPokemon> {
                override fun onFailure(call: Call<InformationPokemon>, t: Throwable) {
                    // tu xu ly =))))
                }

                override fun onResponse(
                    call: Call<InformationPokemon>,
                    response: Response<InformationPokemon>
                ) {
                    if (response.isSuccessful) {
                        mAPokemon.value = response.body()
                    } else {
                        mAPokemon.value = null
                    }
                }
            }
            )
    }

}