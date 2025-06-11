package com.pgr208.mypokemon.data.api

import com.google.gson.annotations.SerializedName

data class PokemonApiResponse(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("types") val types: List<PokemonTypeSlot>
) {
    //Generate picure
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

data class PokemonTypeSlot(
    @SerializedName("type") val type: PokemonType
)

data class PokemonType(
    @SerializedName("name") val typeName: String
)
