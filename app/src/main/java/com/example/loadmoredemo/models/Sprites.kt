package com.example.pokemonapp.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Sprites : Serializable {
    @SerializedName("back_default")
    @Expose
    var backDefault: String? = null

    @SerializedName("back_female")
    @Expose
    var backFemale: String? = null

    @SerializedName("back_shiny")
    @Expose
    var backShiny: String? = null

    @SerializedName("back_shiny_female")
    @Expose
    var backShinyFemale: String? = null

    @SerializedName("front_default")
    @Expose
    var frontDefault: String? = null

    @SerializedName("front_female")
    @Expose
    var frontFemale: String? = null

    @SerializedName("front_shiny")
    @Expose
    var frontShiny: String? = null

    @SerializedName("front_shiny_female")
    @Expose
    var frontShinyFemale: String? = null

    @SerializedName("other")
    @Expose
    var other: Other? = null

    @SerializedName("versions")
    @Expose
    var versions: Versions? = null
}
