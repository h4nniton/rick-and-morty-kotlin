package br.senai.sp.jandira.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.model.Character
import br.senai.sp.jandira.model.Result
import br.senai.sp.jandira.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CharactersList() {

    var characterList by remember {
        mutableStateOf(listOf<Character>())
    }

    // Efetuar chamada para a API

    val callCharacterList = RetrofitFactory()
        .getCharacterService().getAllCharacters()

    callCharacterList.enqueue(object : Callback<Result>{
        override fun onResponse(p0: Call<Result>, p1: Response<Result>) {
            characterList = p1.body()!!.results

        }

        override fun onFailure(p0: Call<Result>, p1: Throwable) {

        }

    })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFFDFD8B6)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Rick and Morty API", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            LazyColumn {
                items(characterList){
                    CharacterCard(character = it)
                    
                }
            }
        }
    }

}

@Composable
fun CharacterCard(character: Character) {
    Card (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults
            .cardColors(
                containerColor = Color(0xFF323D53)
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card (
                modifier = Modifier
                    .size(120.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = ""
                )
            }

            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = character.species)
            }
        }
        
    }
    
}

@Preview(showSystemUi = true)
@Composable
private fun CharactersListPreview() {
    CharactersList()
}