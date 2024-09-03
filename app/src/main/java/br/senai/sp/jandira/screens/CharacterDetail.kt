package br.senai.sp.jandira.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.model.Character
import br.senai.sp.jandira.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun GetCharacterDetails(modifier: Modifier = Modifier) {

    var character by remember {
        mutableStateOf(Character())
    }

    var id by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = id,
            onValueChange = {
                id = it },
            label = {   Text(text = "Digite o id do personagem:")   }
        )
        Button(onClick = {

            val callCharacter = RetrofitFactory()
                .getCharacterService()
                .getCharacterById(id.toInt())

            callCharacter.enqueue(object : Callback<Character> {
                override fun onResponse(p0: Call<Character>, response: Response<Character>) {
                    character = response.body()!!
                }

                override fun onFailure(p0: Call<Character>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }) {
            Text(text = "Buscar personagem")
        }


        Text(text = "Nome: ${character.name}")
        Text(text = "Status: ${character.status}")
        Text(text = "Espécie: ${character.species}")
        Text(text = "Origem: ${character.origin?.name}")
        Text(text = "Localização: ${character.location?.name}")
    }

}

@Preview(showSystemUi = true)
@Composable
private fun CharacterDetailPreview() {
    GetCharacterDetails()
}