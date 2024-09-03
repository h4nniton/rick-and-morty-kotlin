package br.senai.sp.jandira.service

import br.senai.sp.jandira.model.Character
import br.senai.sp.jandira.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService{

    @GET("/character")
    fun getAllCharacters(): Call<Result>

    @GET("/character2")
    fun getCharacterById(@Path("id") id: Int): Call<Character>

}
